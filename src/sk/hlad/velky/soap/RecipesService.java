package sk.hlad.velky.soap;

import javax.jws.WebService;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * Created by Milan Chrastina on 01.03.2016.
 */
@WebService
public class RecipesService {

    private final List<Recipe> recipeList = new CopyOnWriteArrayList<>();

    public RecipesService() {
    }

    /**
     * vrati vsetky recepty v databaze = zozneme
     * @return 
     */
    public List<Recipe> getAllRecipes() {
        return recipeList;
    }

    /**
     * vytvori sa novy recept
     * @param recipe
     * @return UUID
     */
    public int addRecipe(Recipe recipe) {
        int newID = recipeList.size()+1;
        recipe.setId(newID);
        recipeList.add(recipe);
        return newID;
    }

    /**
     * nahradi hodnoty ins premennych receptu s danym UUID a vrati true, alebo false ak recept s danym UUID nexistuje
     * @param recipe
     * @return 
     */
    public int editRecipe(Recipe recipe) {
        Recipe r = getRecipe(recipe.getId());
        if (r != null) {
            r.setAuthor(recipe.getAuthor());
            r.setIngrediencies(recipe.getIngrediencies());
            r.setName(recipe.getName());
            r.setProcess(recipe.getProcess());
            return r.getId();
        } 
        if (r == null) {
            recipe.setId(recipeList.size()+1);
            recipeList.add(recipe);  
            return recipe.getId();
        }
        return -1;
    }

    /**
     * odstrani recept zo zonamu s danym UUID a vrati true, ak sa taky recept nenajde, vrati false
     * @param idString
     * @return 
     */
    public boolean deleteRecipe(String idString) {
        int id = Integer.parseInt(idString);
        for (Recipe r : recipeList) {
            if (r.getId()==(id)) {
                return recipeList.remove(r);
            }
        }
        return false;
    }

    /**
     * vrati recept s danym UUID
     * @param idString
     * @return 
     */
    public Recipe getRecipe(String idString) {
        int id = Integer.parseInt(idString);
        for (Recipe r : recipeList) {
            if (id==(r.getId())) {
                return r;
            }
        }
        return null;
    }

    /**
     * vrati recepty, ktorych nazov, autor alebo postup obsahuju dane klucove slovo
     * @param keyWord
     * @return 
     */
    public List<Recipe> getRecipesByKeyWord(String keyWord) {
        List<Recipe> sublist = new CopyOnWriteArrayList<>();
        for (Recipe r : recipeList) {
            if (r.getAuthor().contains(keyWord) || r.getName().contains(keyWord) || r.getProcess().contains(keyWord)) {
                sublist.add(r);
            }
        }
        return sublist;
    }

    /**
     * vrati recepty, ktorych zoznam ingrediencii, je kratsi ako zoznam danych ingrediencii a vsetky
     * jeho ingrediencie sa nachadzaju v danom zozname
     * @param ings
     * @return 
     */
    public List<Recipe> getRecipesByIng(List<String> ings) {
        List<Recipe> sublist = new CopyOnWriteArrayList<>();
        for (Recipe r : recipeList) {
            if (r.getIngrediencies() != null && r.getIngrediencies().size() <= ings.size()) {
                boolean test = true;
                for (String i : r.getIngrediencies()) {
                    if (!ings.contains(i)) {
                        test = false;
                    }
                }
                if (test) {
                    sublist.add(r);
                }
            }
        }
        return sublist;
    }

    private Recipe getRecipe(int id) {
        for (Recipe r : recipeList) {
            if (r.getId()==(id)) {
                return r;
            }
        }
        return null;
    }
}
