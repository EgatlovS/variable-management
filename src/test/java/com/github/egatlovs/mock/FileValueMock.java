package com.github.egatlovs.mock;

import com.github.egatlovs.variablemanager.annotations.FileValue;
import com.github.egatlovs.variablemanager.annotations.ObjectValue;

import java.io.File;
import java.io.InputStream;

@ObjectValue(storeFields = true)
public class FileValueMock {

    @FileValue(fileName = "myByteFile")
    byte[] bytes;

    @FileValue(fileName = "myInputstreamFile")
    InputStream inputStream;

    @FileValue(fileName = "myFile")
    File file;

    public FileValueMock(byte[] bytes, InputStream inputStream, File file) {
        this.bytes = bytes;
        this.inputStream = inputStream;
        this.file = file;
    }

    public FileValueMock() {
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
