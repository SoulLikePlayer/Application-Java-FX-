package org.example.demo;

class Extension extends Exception{
    private String[] document;
    public String extension;
    public Extension(String document){
        try{
            if(document.contains(".")) {
                this.document = document.split("(?=[^.]+$)", 2);
                extension = "." + this.document[1];
            }
        }catch(Exception e){}
    }
}