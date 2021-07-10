package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EdgeEffect;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOk_KEY = "all_books";
    private static final String ALREADY_READ_BOOK_KEY = "already_read_books";
    private static final String CURRENTLY_READING_BOOK_KEY = "currently_reading_books";
    private static final String WISH_LIST_BOOK_KEY = "wish_list_books";
    private static final String FAVOURITE_BOOK_KEY= "favourite_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;


    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (null == getAllBooks()){
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlReadyReadBooks()){
            editor.putString(ALREADY_READ_BOOK_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWantToReadBooks()){
            editor.putString(WISH_LIST_BOOK_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_BOOK_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavouriteBooks()){
            editor.putString(FAVOURITE_BOOK_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        //TODO: Add initial data

        ArrayList<Book> books = new ArrayList<>();
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum \n" +
                "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur.";

        books.add(new Book(1,"1Q84", "Haruki Marakamani", 1350, "https://images.keizai.biz/newyork_keizai/headline/1319209274_photo.jpg",
                "A work of maddening brelliance", text));
        books.add(new Book(2,"18th Abduction", "James Peterson", 800, "https://www.lubbockonline.com/storyimage/TX/20191229/NEWS/191229101/AR/0/AR-191229101.jpg",
                "A woman's murder club thriller", text));
        books.add(new Book(3, "Skippy Dies", "Paul Murray", 1100, "https://cdn.cliqueinc.com/cache/posts/242278/best-book-titles-242278-1510792787176-product.700x0c.jpg",
                "Skippy during donut eating contest", text));
        books.add(new Book(4, "Milk and Honey", "Rupi Kaur", 500, "https://cdn.cliqueinc.com/cache/posts/274290/love-poem-books-274290-1544048394187-product.700x0c.jpg",
                "Collection of poetry and prose about survival", text));
        books.add(new Book(5, "How To Be A Gentleman", "John Bridges", 1250, "https://i.pinimg.com/originals/dd/62/15/dd6215507a266f0b1e593e8605f48607.jpg",
                "A timely guide to a timely manner", text));
        books.add(new Book(6, "The Doctor got me pregnant", "Vanessa Rose", 900, "https://donkeytime.org/img/240609.jpg",
                "An Exciting, Thrilling, Complete Doctor Romance Novel", text));
        books.add(new Book(7, "The third Call", "Lorhaine Eckhart",999, "https://snickslist.com/wp-content/uploads/2021/07/the-third-call-generic-eb07644d-1366x2048.jpg",
                "Romance and suspence collide in this pulse pounding read", text));
        books.add(new Book(8, "8 Mile", "Eminem", 800, "https://aws.boxofficebuz.com/movies/images/8-mile_4602149.jpg",
                "Every moment is another chance", text));


        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOk_KEY, gson.toJson(books));
        editor.commit();


    }

    public static Utils getInstance(Context context) {
        if(null == instance) {
            instance = new Utils(context);
        }
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOk_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlReadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOK_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WISH_LIST_BOOK_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOK_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOK_KEY, null), type);
        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == id){
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyReadBooks(Book book){
        ArrayList<Book> books = getAlReadyReadBooks();
        if (null !=books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOK_KEY);
                editor.putString(ALREADY_READ_BOOK_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToCurrentlyReadingBooks(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOK_KEY);
                editor.putString(CURRENTLY_READING_BOOK_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToWishList(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISH_LIST_BOOK_KEY);
                editor.putString(WISH_LIST_BOOK_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToFavourites(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null !=books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOK_KEY);
                editor.putString(FAVOURITE_BOOK_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReadingBooks(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOK_KEY);
                        editor.putString(CURRENTLY_READING_BOOK_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromAlreadyReadBooks(Book book){
        ArrayList<Book> books = getAlReadyReadBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(ALREADY_READ_BOOK_KEY);
                    editor.putString(ALREADY_READ_BOOK_KEY, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeFromWishListBooks(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(WISH_LIST_BOOK_KEY);
                    editor.putString(WISH_LIST_BOOK_KEY, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeFromFavouriteBooks(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(FAVOURITE_BOOK_KEY);
                    editor.putString(FAVOURITE_BOOK_KEY, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
}
