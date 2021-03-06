package clean.movies.main.feed.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewsResult(
        @SerializedName("results")
        var results: List<ReviewData>? = null): Parcelable