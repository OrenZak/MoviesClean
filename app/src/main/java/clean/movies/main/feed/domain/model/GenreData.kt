package clean.movies.main.feed.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreData(
        @SerializedName("id")
        var id: Int,

        @SerializedName("name")
        var name: String): Parcelable