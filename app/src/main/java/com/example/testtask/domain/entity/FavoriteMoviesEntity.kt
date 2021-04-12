package com.example.testtask.domain.entity

import android.graphics.Movie
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class FavoriteMoviesEntity(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("original_title") val title: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("poster_path") val img: String? = null,
    var isFavorite: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavoriteMoviesEntity> {
        override fun createFromParcel(parcel: Parcel): FavoriteMoviesEntity {
            return FavoriteMoviesEntity(parcel)
        }

        override fun newArray(size: Int): Array<FavoriteMoviesEntity?> {
            return arrayOfNulls(size)
        }
    }
}
