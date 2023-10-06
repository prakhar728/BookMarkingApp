package com.semanticsquare.thrillio.bgjobs;

import com.semanticsquare.thrillio.dao.BookMarkDao;

public class WebPageDownloaderTask implements Runnable {
    private static BookMarkDao dao = new BookMarkDao();

    final long TIME_FRAME = 3000000000L;

    private boolean downloadAll = false;

    public WebPageDownloaderTask(boolean downloadAll) {
        this.downloadAll = downloadAll;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            //GET WEBLINKS


        }
    }

}