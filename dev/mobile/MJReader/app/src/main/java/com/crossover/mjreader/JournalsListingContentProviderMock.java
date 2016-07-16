package com.crossover.mjreader;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import com.crossover.mjreader.model.Journals;

import java.util.ArrayList;
import java.util.List;


public class JournalsListingContentProviderMock extends ContentProvider {


    public static final String PROVIDER_NAME = "com.acompany.photoi.Journalssearchcontentprovidermock";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME);



    /**
     * An array of sample (dummy) items.
     */
    public static final List<Journals> ITEMS = new ArrayList<Journals>();

    private static final int COUNT = 25;

    private  Journals createJournals(int position) {
        return new Journals(new Integer(position),makeDetails(position));
    }

    private  String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Journals : ").append(position);
        return builder.toString();
    }



    public JournalsListingContentProviderMock() {
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

            Journals Journals = new Journals(i,values.getAsString(PhotoiSQLiteManager.JOURNALS_DATA_COLUMN));
            ITEMS.add(Journals);
        }

        return Uri.withAppendedPath(CONTENT_URI,new Integer(values.size()).toString());
    }

    @Override
    public boolean onCreate() {

        // Add some sample Journalss.
        for (int i = 0; i <= COUNT; i++) {

            Journals Journals = createJournals(i);
            ITEMS.add(Journals);
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
