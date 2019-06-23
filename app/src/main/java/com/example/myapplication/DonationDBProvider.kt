package com.example.myapplication

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri

class DonationDBProvider : ContentProvider() {

    private var myDB: MyDBHandler? = null



    private val PRODUCTS = 1
    private val PRODUCTS_ID = 2
    companion object {
        private val AUTHORITY = "com.example.myapplication.DonationDBProvider"
        private var PRODUCTS_TABLE = DonationTableHandler.table_name
        val CONTENT_URI: Uri = Uri.parse(
            "content://" + AUTHORITY + "/" +
                    PRODUCTS_TABLE
        )
    }

    private val sURIMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {

        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE, PRODUCTS)
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE + "/#",
            PRODUCTS_ID)
    }

    override fun onCreate() : Boolean {
        myDB = MyDBHandler(context, null, null, 1)
        return false
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        val uriType = sURIMatcher.match(uri)
        val sqlDB = myDB!!.writableDatabase
        val id: Long
        when (uriType) {
            PRODUCTS -> id = sqlDB.insert(DonationTableHandler.table_name, null, values)
            else -> throw IllegalArgumentException("Unknown URI: " + uri)
        }
        context.contentResolver.notifyChange(uri, null)
        return Uri.parse(PRODUCTS_TABLE + "/" + id)
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = DonationTableHandler.table_name
        val uriType = sURIMatcher.match(uri)
        when (uriType) {
            PRODUCTS_ID -> queryBuilder.appendWhere(DonationTableHandler.idCol + "="
                    + uri.lastPathSegment)
            PRODUCTS -> {
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }
        val cursor = queryBuilder.query(myDB?.readableDatabase,
            projection, selection, selectionArgs, null, null,
            sortOrder)
        cursor.setNotificationUri(context.contentResolver,
            uri)
        var c = cursor.count
        return cursor
    }



    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}