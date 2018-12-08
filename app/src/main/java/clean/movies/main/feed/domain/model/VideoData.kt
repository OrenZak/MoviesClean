package clean.movies.main.feed.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoData(
        @SerializedName("id")
        var id: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("key")
        var key: String? = null,

        @SerializedName("site")
        var site: String? = null,

        @SerializedName("type")
        var type: String? = null
): Parcelable