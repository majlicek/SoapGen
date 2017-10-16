package sk.hlad.velky.soap;

import java.util.List;

/**
 *
 * Created by Milan Chrastina on 01.03.2016.
 */
public class Recipe {
    
    private int id;
    private String author;
    private List<String> ingrediencies;
    private String name;
    private String process;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String autor) {
        this.author = autor;
    }

    public List<String> getIngrediencies() {
        return ingrediencies;
    }

    public void setIngrediencies(List<String> ingrediencie) {
        this.ingrediencies = ingrediencie;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String postup) {
        this.process = postup;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(name).append(" ").append(process).toString();
    }        
    
}