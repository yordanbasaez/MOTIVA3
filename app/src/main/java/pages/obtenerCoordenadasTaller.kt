package pages

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.google.firebase.firestore.GeoPoint

@SuppressLint("Range")
fun obtenerCoordenadasTaller(tallerId: Int): GeoPoint? {
    val db = SQLiteDatabase.openOrCreateDatabase("TallerDatabase", null)
    val cursor = db.rawQuery("SELECT latitud, longitud FROM talleres WHERE id = ?", arrayOf(tallerId.toString()))

    if (cursor.moveToFirst()) {
        val lat = cursor.getDouble(cursor.getColumnIndex("latitud"))
        val lon = cursor.getDouble(cursor.getColumnIndex("longitud"))
        cursor.close()
        return GeoPoint(lat, lon)
    }

    cursor.close()
    return null
}
