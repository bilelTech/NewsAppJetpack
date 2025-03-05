package com.test.newsappjetpack.data.db.converters

import androidx.room.TypeConverter
import com.test.newsappjetpack.data.db.entity.SourceEntity

class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: SourceEntity): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): SourceEntity {
        val list = source.split(",")
        return SourceEntity(list[0], list[1])
    }
}