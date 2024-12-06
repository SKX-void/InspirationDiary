package com.example.fileSystem.textBlock;


//
//public class activeStringBlock {
//    stringBlock _block;
//    StringBuilder _string;
//    public activeStringBlock(stringBlock active,int pageMax) {
//        this._block = active;
//        this._string = new StringBuilder(pageMax);
//        _string.append(active.getText().toString());
//    }
//
//    public stringBlock get_block() {
//        return _block;
//    }
//    public activeStringBlock saveActive() {
//        if(!_string.toString().equals(_block.getText())) {
//            _block.setText(_string);
//        }
//        return this;
//    }
//    public activeStringBlock changeActive(stringBlock active) {
//        this._block = active;
//        return this;
//    }
//    public activeStringBlock write(StringBuilder str) {
//        _string = str;
//        return this;
//    }
//}
