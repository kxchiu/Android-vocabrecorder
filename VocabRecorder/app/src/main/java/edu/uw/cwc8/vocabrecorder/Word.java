package edu.uw.cwc8.vocabrecorder;

/**
 * Created by Kevin on 2016/6/20.
 */
public class Word {
    public String word;
    public String type1; //defines part of speech
    public String def1;
    public String syn1;
    public String type2;
    public String def2;
    public String syn2;
    public String timeStamp;

    //default constructor that creates an empty word
    public Word(){}

    public Word(String word, String t1, String d1, String s1, String t2, String d2, String s2, String tStamp){
        this.word = word;
        this.type1 = t1;
        this.def1 = d1;
        this.syn1 = s1;
        this.type2 = t2;
        this.def2 = d2;
        this.syn2 = s2;
        this.timeStamp = tStamp;
    }

    public String toString(){
        return this.word;
    }

    public void setTimestamp(String timestamp) {
        this.timeStamp = timestamp;
    }
}
