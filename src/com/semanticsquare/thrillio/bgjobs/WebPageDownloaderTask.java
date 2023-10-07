package com.semanticsquare.thrillio.bgjobs;

import com.semanticsquare.thrillio.dao.BookMarkDao;
import com.semanticsquare.thrillio.entities.WebLink;
import com.semanticsquare.thrillio.util.HttpConnect;
import com.semanticsquare.thrillio.util.IOUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WebPageDownloaderTask implements Runnable {
    private static BookMarkDao dao = new BookMarkDao();

    final long TIME_FRAME = 3000000000L;

    private boolean downloadAll = false;
    ExecutorService downloadExecutor = Executors.newFixedThreadPool(5);
    public WebPageDownloaderTask(boolean downloadAll) {
        this.downloadAll = downloadAll;
    }

    private static class Downloader<T extends WebLink> implements Callable<T>{
        private T webLink;
        public Downloader(T webLink){
            this.webLink=webLink;
        }
        public T call(){
            try{
                if(!webLink.getUrl().endsWith(".pdf")) {
                    webLink.setDownloadStatus(WebLink.DownloadStatus.FAILED);
                    String htmlPage = HttpConnect.download(webLink.getUrl());
                    webLink.setHtmlPage(htmlPage);
                }else{
                    webLink.setDownloadStatus(WebLink.DownloadStatus.NOT_ELIGIBLE);
                }
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch(URISyntaxException e){
                e.printStackTrace();
            }
            return  webLink;
        }
    }
    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            //GET WEBLINKS
            List<WebLink> weblinks =  getWebLinks();

            if(weblinks.size()>0){
                download(weblinks);
            }
            else{
                System.out.println("No new web Links to Download!");
            }

            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void download(List<WebLink> webLinks){
        List<Downloader<WebLink>> tasks = getTasks(webLinks);
        List<Future<WebLink>> futures = new ArrayList<>();

        try
        {
            futures = downloadExecutor.invokeAll(tasks,TIME_FRAME,TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        for(Future<WebLink> future:futures){
            try
            {
                if(!future.isCancelled()){
                    WebLink webLink=  future.get();
                    String webPage  = webLink.getHtmlPage();
                    System.out.println("The webpage to download is " + webPage);
                    if(webPage!=null){
                        IOUtil.write(webPage,webLink.getId());
                        webLink.setDownloadStatus(WebLink.DownloadStatus.SUCCESS);
                        System.out.println("Download Success" + webLink.getUrl());
                    }else{
                        System.out.println("Webpage not downloaded " + webLink.getUrl());
                    }
                }
                else{
                    System.out.println("\n Task is cancelled -> " + Thread.currentThread());
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            catch (ExecutionException e){
                e.printStackTrace();
            }
        }
    }

    private List<Downloader<WebLink>> getTasks(List<WebLink> webLinks) {
        List<Downloader<WebLink>> tasks = new ArrayList<>();
        for(WebLink webLink:webLinks){
            tasks.add(new Downloader<WebLink>(webLink));
        }
        return tasks;
    }

    private List<WebLink> getWebLinks() {
        List<WebLink> webLinks = null;

        if(downloadAll){
            webLinks = dao.getAllWebLinks();
            downloadAll =false;
        }
        else{
           webLinks =  dao.getWebLinks(WebLink.DownloadStatus.NOT_ATTEMPTED);
        }

        return  webLinks;
    }

}