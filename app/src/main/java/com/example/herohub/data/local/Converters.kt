package com.example.herohub.data.local

import androidx.room.TypeConverter
import com.example.herohub.data.remote.model.Thumbnail

class Converters {
    @TypeConverter
    fun thumbnailToImagePath(thumbnail: Thumbnail): String {
        return thumbnail.path.toString()
    }

    @TypeConverter
    fun imagePathToThumbnail(imagePath: String): Thumbnail {
        return Thumbnail(imagePath)
    }
}