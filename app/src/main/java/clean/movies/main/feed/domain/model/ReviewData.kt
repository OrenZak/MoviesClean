package clean.movies.main.feed.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewData(
        @SerializedName("id")
        var id: String,

        @SerializedName("author")
        var author: String,

        @SerializedName("content")
        var content: String? = null
): Parcelable