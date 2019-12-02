package com.gacode.relaunchx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.koreaderhistfavparser.KOReaderBook;
import org.koreaderhistfavparser.KOReaderHistFav;

class KOReaderHistFavRelaunch extends KOReaderHistFav {

    KOReaderHistFavRelaunch() throws FileNotFoundException {
    }

    KOReaderHistFavRelaunch(String koreaderDirectoryPath) throws FileNotFoundException {
        super(koreaderDirectoryPath);
    }

    HashMap<String, Integer> getHistoryMap() {
        HashMap<String, Integer> historyMap = new HashMap<>();
        for (KOReaderBook book : getLibrary().values()) {
            if (book.getFinished())
                historyMap.put(book.getFilePath(), ReLaunchApp.FINISHED);
            else
                historyMap.put(book.getFilePath(), ReLaunchApp.READING);
        }
        return historyMap;
    }

    ArrayList<String[]> getHistoryList() {
        ArrayList<String[]> historyList = new ArrayList<>();
        for (KOReaderBook book : getLibrary().values()) {
            String [] filePathStatus;
            if (book.getFinished())
                filePathStatus = new String[] {book.getFilePath(), "FINISHED"};
            else
                filePathStatus = new String[] {book.getFilePath(), "READING"};
            historyList.add(filePathStatus);
        }
        return historyList;
    }

    ArrayList<String[]> getLastOpenedList() {
        return filePathList(getHistory());
    }

    ArrayList<String[]> getFavoritesList() {
        return filePathList(getFavorites());
    }

    Boolean setBookFinished(String filePath) {
        return getBook(filePath).setFinished();
    }

    Boolean setBookReading(String filePath) {
        return getBook(filePath).setReading();
    }

    private ArrayList<String[]> filePathList(ArrayList<KOReaderBook> bookList) {
        ArrayList<String[]> filePathList = new ArrayList<>();
        //for (KOReaderBook book : bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            //File file = new File(book.getFilePath());
            File file = new File(bookList.get(i).getFilePath());
            if (!file.exists())
                continue;
            String[] fileParentName = new String[] {file.getParent(), file.getName()};
            filePathList.add(fileParentName);
        }
        return filePathList;
    }

}
