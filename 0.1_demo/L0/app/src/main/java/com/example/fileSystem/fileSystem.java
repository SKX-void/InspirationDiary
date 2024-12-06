package com.example.fileSystem;

import com.example.fileSystem.error.error;
import com.example.fileSystem.textBlock.stringBlock;


import java.time.LocalDateTime;
import java.util.TreeMap;

public class fileSystem {
    private static int pageMax=10000;
    //private static int pageShow;
    String _name;
    private TreeMap<Integer, stringBlock> _list = new TreeMap<>();

    fileSystem(){
        _name="newFile";//+LocalDateTime.now().toString();
    }

    String getName(){
        return _name;
    }
    public fileSystem setName(String name){
        this._name =name;
        return this;
    }
    public static int getPageMax(){
        return pageMax;
    }
    public static void setPageMax(int _pageMax){
        pageMax = _pageMax;
    }

    public boolean appendPage(){
        stringBlock nP=new stringBlock(0);
        if(!_list.isEmpty()){
            nP.setIndex(_list.lastEntry().getValue().getIndex()+1);
        }
        _list.put(nP.getIndex(),nP);
        return true;
    }

    public TreeMap<Integer,stringBlock> list(){
        return _list;
    }

    public void insertPage(Integer index){
        if(index>=(_list.lastEntry().getValue().getIndex()+1)){
            appendPage();
        }else {

            Integer p=_list.lastKey()+1;
            for(;p>index;p--){
                _list.put(p,_list.get(p-1));
            }
            _list.put(index,new stringBlock(p));
        }
    }
    public void collate(){
        collateTool c=new collateTool(pageMax,_list);
    }
    public void collate(String endByte){
        collateTool c=new collateTool(endByte,pageMax,_list);
    }
}



class collateTool{
    String endByte="。";
    int maxByte;
    //int nextStringLength;
    TreeMap<Integer, stringBlock> target;

    collateTool(String endByte, int maxByte, TreeMap<Integer, stringBlock> target){
        this.endByte=endByte;
        this.maxByte=maxByte;
        this.target=target;
    }
    collateTool(int maxByte, TreeMap<Integer, stringBlock> target){
        this.endByte=endByte;
        this.maxByte=maxByte;
        this.target=target;
    }
    public void start(){
        //Iterator<Map.Entry<Integer, stringBlock>> iterator = target.entrySet().iterator();
        Integer pageNum=target.lastKey()+1;
        Integer left=0,right=1;
        StringBuilder temp=new StringBuilder();

        for(;right<pageNum;){
            String strLeft = target.get(left).getText().toString();
            String strRight = target.get(right).getText().toString();

            if(strRight.isEmpty()){
                right++;
                continue;
            }

            if (strLeft.length() > maxByte) {
                int i = strLeft.lastIndexOf(endByte);
                if(i==-1){
                    i=strLeft.length()-endByte.length()-maxByte;
                }
                target.get(right).getText().insert(0, strLeft.substring(i + endByte.length()));
                target.get(left).getText().delete(i + endByte.length(), target.get(left).getText().length());
                continue;
            }

            int nextStringLength = strRight.indexOf(endByte);
            if(nextStringLength==-1){
                nextStringLength=strRight.length();
            }
            if (strLeft.length() + nextStringLength < maxByte) {
                target.get(left).getText().append(strRight.substring(0, nextStringLength));
                target.get(right).getText().delete(0, nextStringLength);
                continue;
            }

            if(strLeft.length() + nextStringLength > maxByte && strLeft.length() <= maxByte){
                left++;
                if(left>=right)right=left+1;
                continue;
            }

            throw new error("sort err");

        }
        for(pageNum-=1;target.get(pageNum).getText().length()==0&&target.get(pageNum).getTitle().isEmpty();pageNum--){
            target.remove(pageNum);
        }

    }

}
