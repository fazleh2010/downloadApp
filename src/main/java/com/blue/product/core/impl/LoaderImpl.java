/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blue.product.core.impl;

import com.blue.product.core.api.Loader;
import com.blue.product.exception.LoaderException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.nio.charset.StandardCharsets;
import javax.activation.MimetypesFileTypeMap;
import com.blue.product.core.constant.Message;
import com.blue.product.core.constant.FileType;
import java.io.FileNotFoundException;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class LoaderImpl implements Message, Loader, FileType {

    private final String resourceLocation;
    private  Set<String> inputs;


    public LoaderImpl(File inputfile) throws LoaderException {
        this.resourceLocation = inputfile.getAbsoluteFile().getParent();
        if (isValid(inputfile, TEXT_FILE)) {
            try {
                InputStream inputStream = new FileInputStream(inputfile);
                this.inputs = this.getLinesFromFile(inputStream);
            } catch (FileNotFoundException ex) {
                throw new LoaderException(ex.getMessage());
            }

        }
    }

    private Set<String> getLinesFromFile(InputStream inputStream) throws LoaderException {
        BufferedReader reader = null;
        Set<String> inputs = new HashSet<String>();
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8.name()));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    inputs.add(line);
                }
            }

        } catch (Exception ex) {
            throw new LoaderException(ex.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new LoaderException(ex.getMessage());
            }
        }
        return inputs;
    }

    private boolean isValid(File inputFile, String FileType) throws LoaderException {
        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        if (fileTypeMap.getContentType(inputFile.getName()).equalsIgnoreCase(FileType)) {
            return true;
        } else {
            throw new LoaderException(Message.NOT_TEXT_FILE);
        }

    }

    @Override
    public Set<String> getInputs() {
        return this.inputs;
    }

    public String getResourceLocation() {
        return resourceLocation;
    }

}
