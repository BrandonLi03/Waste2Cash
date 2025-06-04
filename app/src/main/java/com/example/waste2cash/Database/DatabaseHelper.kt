    package com.example.waste2cash.Database

    import android.content.ContentValues
    import android.content.Context
    import android.database.sqlite.SQLiteDatabase
    import android.database.sqlite.SQLiteOpenHelper
    import android.location.Address
    import android.widget.Toast
    import androidx.core.content.contentValuesOf
    import com.example.waste2cash.Model.Category
    import com.example.waste2cash.Model.User
    import java.sql.Time
    import java.util.Date

    class DatabaseHelper(var context: Context): SQLiteOpenHelper(context, "waste2cash", null, 1) {

        fun createUserTable(db: SQLiteDatabase?) {
            val query = "create table if not exists users (userId integer primary key autoincrement, " +
                    "username text," +
                    "email text, " +
                    "password text)"
            db?.execSQL(query)
        }

        fun createTransactionTable(db: SQLiteDatabase?){
            val query = "create table if not exists transactions (transactionId integer primary key autoincrement, " +
                    "userId integer," +
                    "category text," +
                    "quantity integer," +
                    "date text, " +
                    "time text, " +
                    "address text," +
                    "foreign key (userId) references users(userId))"
            db?.execSQL(query)
        }

        fun createCategoryTable(db: SQLiteDatabase?){
            val query = "create table if not exists categories (categoryId integer primary key autoincrement, " +
                    "categoryName text," +
                    "categoryImg text)"
            db?.execSQL(query)
        }

        fun insertCategory(db: SQLiteDatabase?){
            val query = "insert into categories (categoryName, categoryImg) values " +
                    "('paper', null)," +
                    "('glass', null)," +
                    "('plastic', null)," +
                    "('metal', null)," +
                    "('oil', null)"
            db?.execSQL(query)
        }

        override fun onCreate(db: SQLiteDatabase?) {
            createUserTable(db)
            createTransactionTable(db)
            createCategoryTable(db)
            insertCategory(db)
        }

        fun insertUser(username: String, email: String, password: String){
            val db = writableDatabase
            val cv = ContentValues()
            cv.put("username", username)
            cv.put("email", email)
            cv.put("password", password)
            val result = db.insert("users", null, cv)
            if (result == -1.toLong()){
                Toast.makeText(context, "Sign Up Failed", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
            }
            db.close()
        }

        fun insertTransaction(userId: Int, category: String, quantity: Int ,date: String, time: String, address: String){
            val db = writableDatabase
            val cv = ContentValues()
            cv.put("userId", userId)
            cv.put("category", category)
            cv.put("quantity", quantity)
            cv.put("date", date)
            cv.put("time", time)
            cv.put("address", address)
            db.insert("transactions", null, cv)
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
                    user.email = result.getString(2)
                    user.password = result.getString(3)
                    list.add(user)

                } while (result.moveToNext())
            }

            result.close()
            db.close()
            return list
        }

        fun readCategory() : MutableList<Category>{
            var list : MutableList<Category> = ArrayList()
            val db = this.readableDatabase
            val query = "SELECT * FROM categories"
            var result = db.rawQuery(query, null)

            if (result.moveToFirst()) {
                do {
                    var category = Category()
                    category.categoryId = result.getString(0).toInt()
                    category.categoryName = result.getString(1)
                    category.categoryImg = result.getString(2)?: ""
                    list.add(category)
                } while (result.moveToNext())
            }

            result.close()
            db.close()
            return list
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("drop table if exists users")
            db?.execSQL("drop table if exists categories")
            onCreate(db)
        }
    }