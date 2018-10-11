package com.arafeh.base.view.adapters

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import butterknife.BindView
import com.arafeh.base.R
import com.arafeh.base.data.enum.ReportType
import com.arafeh.base.data.om.Report
import com.arafeh.base.internal.core.BaseAdapter
import com.arafeh.base.internal.core.BaseViewHolder


/**
 * Created by Arafeh on 7/14/2018.
 */
class ReportAdapter(context: Context, link: ArrayList<Report>, var onItemClick: (Report) -> Unit) : BaseAdapter<Report>(context, link) {
    override fun layout(): Int {
        return R.layout.adapter_report
    }

    override fun viewHolder(view: View, item: Report): BaseViewHolder<Report> {
        return ViewHolder(view, item)
    }

    inner class ViewHolder(view: View, val report: Report) : BaseViewHolder<Report>(view, report) {
        @BindView(R.id.like)
        lateinit var btLike: ImageButton

        @BindView(R.id.dislike)
        lateinit var btDislike: ImageButton

        @BindView(R.id.logoImg)
        lateinit var logoImg: ImageView

        @BindView(R.id.starImg)
        lateinit var starImg: ImageView

        init {
            logoImg.setOnClickListener { onItemClick(report) }
        }

        override fun bind() {
            when (report.type) {
                ReportType.RADAR -> logoImg.setImageResource(R.drawable.img_radar)
                ReportType.BARRIER -> logoImg.setImageResource(R.drawable.img_defence)
            }
            when {
                report.acc > 0.75f -> starImg.setImageResource(R.drawable.img_star_100)
                report.acc > 0.5f -> starImg.setImageResource(R.drawable.img_star_75)
                report.acc > 0.25f -> starImg.setImageResource(R.drawable.img_star_50)
                report.acc > 0f -> starImg.setImageResource(R.drawable.img_star_25)
                report.acc == 0f -> starImg.setImageResource(R.drawable.img_star_0)
            }
        }

        override fun reBind() {

        }

    }


}