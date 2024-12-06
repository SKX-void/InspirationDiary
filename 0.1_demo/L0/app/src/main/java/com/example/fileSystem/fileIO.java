package com.example.fileSystem;

import com.example.fileSystem.error.errIndex;
import com.example.fileSystem.error.errPath;
import com.example.fileSystem.error.error;
import com.example.fileSystem.textBlock.statusBit;
import com.example.fileSystem.textBlock.stringBlock;
import com.example.fileSystem.tool.result;

import java.io.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class fileIO {
    String _path;
    fileSystem _file;
    fileIO(fileSystem f) {
        this._file = f;
    }

    public fileIO setPath(String path) {
        this._path = path;
        return this;
    }

    public fileIO writeFile(){
        try{
            //ObjectOutputStream objos = new ObjectOutputStream(os);
            File folder = new File(_path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            for (Map.Entry<Integer, stringBlock> entry : _file.list().entrySet()) {
                if((entry.getValue().getStatus()| statusBit.load)==0)continue;
                //FileOutputStream os = new FileOutputStream(path+"/"+entry.getKey()+".txt");
                BufferedWriter bfw = new BufferedWriter(new FileWriter(_path+"/"+_file.getName()+"/"+entry.getKey().toString()+".txt"));
                bfw.write(entry.getValue().getIndex().toString());
                bfw.newLine();
                bfw.write(String.valueOf(entry.getValue().getStatus()) );
                bfw.newLine();
                bfw.write(entry.getValue().getTitle());
                bfw.newLine();
                bfw.write(entry.getValue().getText().toString());
                bfw.close();
            }
        } catch (IOException e) {
            throw new errPath("write file error");
        }
        return this;
    }

    public result<Boolean> readFile(){
        if(!_file.list().isEmpty()){
            return new result<>(new error("file is not empty"));
        }
        List<String> indexes = new ArrayList<>();
        File directory = new File(_path);

        // 检查目录是否存在
        if (!directory.exists())return new result<>(new errPath("error path"));
                //&& directory.isDirectory())
        File[] files = directory.listFiles();
        if (files== null) return new result<>(true);

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                indexes.add(file.getPath());
            }
        }

        for (String indexPath : indexes) {
            try {
                BufferedReader bfr=new BufferedReader(new FileReader(indexPath));
                stringBlock temp=new stringBlock(Integer.valueOf(bfr.readLine()));
                temp.setStatus(Integer.valueOf(bfr.readLine()));
                temp.setTitle(bfr.readLine());
                if((temp.getStatus()|statusBit.load)==0){
                    bfr.close();
                    continue;
                }
                String line;
                StringBuilder text=new StringBuilder("");
                while((line=bfr.readLine())!=null){
                    text.append(line);
                }
                temp.setText(new StringBuilder(text.toString()));
                bfr.close();
            } catch (Exception e) {
                return new result<>(new errPath(indexPath+"not found"));
            }

        }
        return new result<>(true);
    }

    public result<Boolean> loadFile(Integer index){
        try {

            BufferedReader bfr=new BufferedReader(new FileReader(_path+"/"+index.toString()+".txt"));
            stringBlock temp=new stringBlock(Integer.valueOf(bfr.readLine()));
            temp.setStatus(Integer.valueOf(bfr.readLine()));
            temp.setTitle(bfr.readLine());
            String line;
            StringBuilder text=new StringBuilder("");
            while((line=bfr.readLine())!=null){
                text.append(line);
            }
            temp.setText(new StringBuilder(text.toString()));
            bfr.close();

            _file.list().get(index).loading(temp);

        } catch (Exception e){
            return new result<>(new  errIndex("File not found"));
            // new errIndex("File not found");
        }
        return new result<>(true);
    }
}
