package com.example.fileSystem;

//import fileSystem.textBlock.activeStringBlock;

import com.example.fileSystem.error.errPath;
import com.example.fileSystem.error.error;
import com.example.fileSystem.textBlock.stringBlock;

public class fileSystemObject {
    fileSystem _fileSystem;
    fileIO _fileIO;
    stringBlock _activeStringBlock;
    public fileSystemObject() {
        _fileSystem = new fileSystem();
        _fileIO = null;

        _fileSystem.appendPage();
        _activeStringBlock =_fileSystem.list().lastEntry().getValue();//,fileSystem.getPageMax()
    }
    public fileSystemObject(String path) {
        _fileSystem = new fileSystem();
        _fileIO = new fileIO(_fileSystem);
        _fileIO.setPath(path)
                .readFile();
        if(_fileSystem.list().isEmpty())_fileSystem.appendPage();
        _activeStringBlock =_fileSystem.list().lastEntry().getValue();
    }

    public fileSystemObject appendPage() {
        _fileSystem.appendPage();
        _activeStringBlock=_fileSystem.list().lastEntry().getValue();
                //.saveActive()
                //.changeActive(_fileSystem.list().lastEntry().getValue());
        return this;
    }
    public fileSystemObject writePage(StringBuilder str) {
        _activeStringBlock.setText(str);
        return this;
    }

    public fileSystemObject setFileIO(String path) {
        if(_fileIO != null){
            throw new error("has file");
        }
        _fileIO =new fileIO(_fileSystem);
        _fileIO.setPath(path);
        return this;
    }
    public fileSystemObject saveFile() {
        if(_fileIO==null)throw new errPath("no fileIO");
        _fileIO.writeFile();
        return this;
    }
    public stringBlock activeStringBlock(){
        return _activeStringBlock;
    }
    public fileSystemObject set_activeStringBlock(Integer p){
        _activeStringBlock=_fileSystem.list().get(p);
        return this;
    }
    public fileSystem fileSystem(){
        return _fileSystem;
    }
//    public fileSystemObject openFile() {
//        return this;
//    }
}
