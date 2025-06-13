package com.example.waste2cash.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.waste2cash.Model.User

class DatabaseHelper(var context: Context): SQLiteOpenHelper(context, "waste2cash", null, 1) {

    fun createUserTable(db: SQLiteDatabase?) {
        val query = "create table if not exists users (userId integer primary key autoincrement," +
                "username text," +
                "phoneNumber text," +
                "password text," +
                "address text," +
                "money integer)"
        db?.execSQL(query)
    }

    fun createUserTransactionTable(db: SQLiteDatabase?){
        val query = "create table if not exists usertransactions (usertransactionId integer primary key autoincrement," +
                "userId integer," +
                "categoryId integer," +
                "weight integer," +
                "dateTime text," +
                "foreign key (userId) references users(userId)," +
                "foreign key(categoryId) references categories(categoryId))"
        db?.execSQL(query)
    }

    fun createCategoryTable(db: SQLiteDatabase?){
        val query = "create table if not exists categories (categoryId integer primary key autoincrement," +
                "categoryName text," +
                "categoryPrice integer)"
        db?.execSQL(query)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createUserTable(db)
        createUserTransactionTable(db)
        createCategoryTable(db)

        insertCategory(db)
    }

    fun insertCategory(db: SQLiteDatabase?){
        val query = "insert into categories (categoryName, categoryPrice) values ('Paper', 2000)," +
                "('Glass Bottle', 3000)," +
                "('Plastic', 3000)," +
                "('Metal', 5000)," +
                "('Oil', 4000)"
        db?.execSQL(query)
    }

    fun insertUser(username: String, phoneNumber: String, password: String){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("phoneNumber", phoneNumber)
        cv.put("password", password)
        val result = db.insert("users", null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Sign Up Failed", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun readUser() : MutableList<User>{
        var list : MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM users"
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var user = User()
                user.userId = result.getInt(0)
                user.username = result.getString(1)
                user.phoneNumber = result.getString(2)
                user.password = result.getString(3)
                user.address = result.getString(4)
                user.money = result.getInt(5)
                list.add(user)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun updateUsername(userId: Int, newUsername: String){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("username", newUsername)
        db.update("users", cv, "userId = ?", arrayOf(userId.toString()))
        db.close()
    }

    fun updateAddress(userId: Int, newAddress: String){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("address", newAddress)
        db.update("users", cv, "userId = ?", arrayOf(userId.toString()))
        db.close()
    }

    fun updatePhoneNumber(userId: Int, newPhoneNumber: String){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("phoneNumber", newPhoneNumber)
        db.update("users", cv, "userId = ?", arrayOf(userId.toString()))
        db.close()
    }

    fun getMoney(userId: Int): Int{
        val db = this.readableDatabase
        val query = "SELECT money FROM users WHERE userId = ?"
        val cursor = db.rawQuery(query, arrayOf(userId.toString()))
        var money = 0

        if (cursor.moveToFirst()) {
            money = cursor.getInt(cursor.getColumnIndexOrThrow("money"))
        }

        cursor.close()
        return money
    }

    fun getCategoryPrice(categoryId: Int): Int {
        val db = this.readableDatabase
        val query = "SELECT categoryPrice FROM categories WHERE categoryId = ?"
        val cursor = db.rawQuery(query, arrayOf(categoryId.toString()))
        var price = 0

        if (cursor.moveToFirst()) {
            price = cursor.getInt(cursor.getColumnIndexOrThrow("categoryPrice"))
        }

        cursor.close()
        return price
    }

    fun updateUserMoney(userId: Int, categoryId: Int, weight: Int): Boolean {
        val db = writableDatabase

        val categoryPrice = getCategoryPrice(categoryId)
        val totalEarnings = categoryPrice * weight

        val currentMoney = getMoney(userId)
        val newMoney = currentMoney + totalEarnings

        val cv = ContentValues()
        cv.put("money", newMoney)

        val result = db.update("users", cv, "userId = ?", arrayOf(userId.toString()))
        db.close()

        return result > 0
    }

    fun insertTransaction(userId: Int, categoryId: Int, weight: Int ,dateTime: String){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("userId", userId)
        cv.put("categoryId", categoryId)
        cv.put("weight", weight)
        cv.put("dateTime", dateTime)
        val result = db.insert("usertransactions", null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Transaction Failed", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Transaction Successful", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists users")
        db?.execSQL("drop table if exists usertransactions")
        db?.execSQL("drop table if exists categories")
        onCreate(db)
    }
}