package com.example.myspacex.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchUi

class LaunchDetailsAdapter(private val items: List<LaunchUi<*>>) :
    RecyclerView.Adapter<LaunchDetailViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        LaunchDetailsViewType.MISSION_NAME.ordinal -> MissionNameViewHolder(parent.makeView(R.layout.title))
        LaunchDetailsViewType.FLIGHT_NUMBER.ordinal -> FlightNumberViewHolder(parent.makeView(R.layout.subtitle))
        LaunchDetailsViewType.LAUNCH_YEAR.ordinal -> LaunchYearViewHolder(parent.makeView(R.layout.subtitle))
        LaunchDetailsViewType.LAUNCH_DATE.ordinal -> LaunchDateViewHolder(parent.makeView(R.layout.subtitle))
        LaunchDetailsViewType.ROCKET.ordinal -> RocketViewHolder(parent.makeView(R.layout.subtitle))
        LaunchDetailsViewType.SHIPS.ordinal -> ShipsViewHolder(parent.makeView(R.layout.subtitle))
        LaunchDetailsViewType.LAUNCH_PLACE.ordinal -> LaunchPlaceViewHolder(parent.makeView(R.layout.subtitle))
        LaunchDetailsViewType.LAUNCH_SUCCESS.ordinal -> LaunchSuccessViewHolder(parent.makeView(R.layout.checkbox))
        LaunchDetailsViewType.LINK.ordinal -> LinkViewHolder(parent.makeView(R.layout.link))
        LaunchDetailsViewType.IMAGE.ordinal -> ImageViewHolder(parent.makeView(R.layout.image))
        LaunchDetailsViewType.PDF.ordinal -> PDFViewHolder(parent.makeView(R.layout.link))
        LaunchDetailsViewType.DETAILS.ordinal -> DetailsViewHolder(parent.makeView(R.layout.subtitle))
        else -> throw UnsupportedOperationException("unknown type of item")
    }

    override fun getItemViewType(position: Int) = when (items[position]) {
        is LaunchUi.MissionName -> LaunchDetailsViewType.MISSION_NAME.ordinal
        is LaunchUi.FlightNumber -> LaunchDetailsViewType.FLIGHT_NUMBER.ordinal
        is LaunchUi.LaunchYear -> LaunchDetailsViewType.LAUNCH_YEAR.ordinal
        is LaunchUi.LaunchDate -> LaunchDetailsViewType.LAUNCH_DATE.ordinal
        is LaunchUi.Rocket -> LaunchDetailsViewType.ROCKET.ordinal
        is LaunchUi.Ships -> LaunchDetailsViewType.SHIPS.ordinal
        is LaunchUi.LaunchPlace -> LaunchDetailsViewType.LAUNCH_PLACE.ordinal
        is LaunchUi.LaunchSuccess -> LaunchDetailsViewType.LAUNCH_SUCCESS.ordinal
        is LaunchUi.LinkTitle -> LaunchDetailsViewType.LINK.ordinal
        is LaunchUi.Image -> LaunchDetailsViewType.IMAGE.ordinal
        is LaunchUi.PDF -> LaunchDetailsViewType.PDF.ordinal
        is LaunchUi.Details -> LaunchDetailsViewType.DETAILS.ordinal
    }

    override fun onBindViewHolder(holder: LaunchDetailViewHolder<*>, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}

abstract class LaunchDetailViewHolder<T : LaunchUi<*>>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: Any)
}

private enum class LaunchDetailsViewType {
    MISSION_NAME,
    FLIGHT_NUMBER,
    LAUNCH_YEAR,
    LAUNCH_DATE,
    ROCKET,
    SHIPS,
    LAUNCH_PLACE,
    LAUNCH_SUCCESS,
    LINK,
    IMAGE,
    PDF,
    DETAILS
}

fun ViewGroup.makeView(@LayoutRes layoutResId: Int): View =
    LayoutInflater.from(this.context).inflate(layoutResId, this, false)