package com.acompany.photoi;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import com.acompany.photoi.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSearchContentProviderMock extends ContentProvider {


    public static final String PROVIDER_NAME = "com.acompany.photoi.productsearchcontentprovidermock";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME);



    /**
     * An array of sample (dummy) items.
     */
    public static final List<Product> ITEMS = new ArrayList<Product>();

    private static final int COUNT = 25;

    private  Product createProduct(int position) {
        return new Product(new Integer(position),makeDetails(position));
    }

    private  String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Product : ").append(position);
        return builder.toString();
    }



    public ProductSearchContentProviderMock() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        for (int i=0;i < values.size();i++) {

            Product product = new Product(i,values.getAsString(PhotoiSQLiteManager.PRODUCT_DATA_COLUMN));
            ITEMS.add(product);
        }

        return Uri.withAppendedPath(CONTENT_URI,new Integer(values.size()).toString());
    }

    @Override
    public boolean onCreate() {

        // Add some sample products.
        for (int i = 0; i <= COUNT; i++) {

            Product product = createProduct(i);
            ITEMS.add(product);
        }

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        MatrixCursor cursor = new MatrixCursor(new String[] {"id","description"},ITEMS.size());
        for (int i=0;i < cursor.getCount();i++) {

            cursor.addRow(new Object[]{new Integer(ITEMS.get(i).getId()),new String(ITEMS.get(i).getName())});
        }

        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
