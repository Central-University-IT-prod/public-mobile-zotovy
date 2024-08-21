package dev.zotov.prod_app.data.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter: TypeAdapter<LocalDate>() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun write(out: JsonWriter, value: LocalDate) {
        out.value(DateTimeFormatter.ISO_LOCAL_DATE.format(value))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun read(input: JsonReader): LocalDate {
        return LocalDate.parse(input.nextString())
    }

}