package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private Button btnAddToCurrentlyReading, btnAddToAlreadyRead, btnAddToWantToRead, btnAddToFavourites;
    private TextView textBookNameGiven, textViewAuthorGiven, textPagesGiven, textLongDescription;
    private ImageView imageBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        /**
         * Getting data from recView from our AllBooksActivity
         */
        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance(BookActivity.this).getBookById(bookId);
                if (null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleWishList(incomingBook);
                    handleFavourite(incomingBook);

                }
            }
        }
    }

    /**
     * Adding a book to favourite
     * Disabling the button after adding
     * @param book
     */
    private void handleFavourite(final Book book) {
        ArrayList<Book> favouriteBooks = Utils.getInstance(this).getFavouriteBooks();

        boolean existFavouriteBooks = false;

        for (Book b: favouriteBooks){
            if (b.getId() == book.getId()){
                existFavouriteBooks = true;
            }
        }

        if (existFavouriteBooks){
            btnAddToFavourites.setEnabled(false);
        }
        else {
            btnAddToFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavourites(book)){
                        Toast.makeText(BookActivity.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavouriteBooksActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Not Added. Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Adding a book to WishList
     * Disabling the button after adding
     * @param book
     */
    private void handleWishList(final Book book) {
        ArrayList<Book> wishListBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existWantToReadBooks = false;

        for (Book b: wishListBooks){
            if (b.getId() == book.getId()){
                existWantToReadBooks =true;
            }
        }
        if (existWantToReadBooks){
            btnAddToWantToRead.setEnabled(false);
        }
        else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWishList(book)){
                        Toast.makeText(BookActivity.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WishListActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Not Added. Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Adding a book to CurrentlyReading
     * Disabling the button after adding
     * @param book
     */
    private void handleCurrentlyReading(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b: currentlyReadingBooks){
            if (b.getId() == book.getId()){
                existInCurrentlyReadingBooks = true;
            }
        }
        if (existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }
        else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReadingBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingBookActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(BookActivity.this, "Not Added, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Adding a book to alreadyReadBooks
     * Disabling the button after adding
     * @param book
     */

    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlReadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b: alreadyReadBooks){
            if (b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }
        else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyReadBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(BookActivity.this,"Not Added, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book){
        textBookNameGiven.setText(book.getName());
        textViewAuthorGiven.setText(book.getAuthor());
        textPagesGiven.setText(String.valueOf(book.getPages()));
        textLongDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImgUrl())
                .into(imageBook);
    }

    //initializing views

    private void initViews() {
        imageBook = findViewById(R.id.imageBook);

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToFavourites = findViewById(R.id.btnAddToFavourites);

        textBookNameGiven = findViewById(R.id.textBookNameGiven);
        textViewAuthorGiven = findViewById(R.id.textViewAuthorGiven);
        textPagesGiven = findViewById(R.id.textPagesGiven);
        textLongDescription = findViewById(R.id.textLongDescription);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}