package com.nikpanfilov.superfit.imagelist.ui

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikpanfilov.superfit.imagelist.R
import com.nikpanfilov.superfit.imagelist.databinding.ItemDateBinding
import com.nikpanfilov.superfit.imagelist.databinding.ItemImageBinding
import com.nikpanfilov.superfit.imagelist.presentation.ImageListEvent
import com.nikpanfilov.superfit.imagelist.presentation.ListItem

class ListAdapter(private val toImage: (ImageListEvent.NavigateToImage) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	var data: List<ListItem> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return when (viewType) {
			R.layout.item_image -> {
				ImageViewHolder(ItemImageBinding.inflate(inflater, parent, false))
			}

			R.layout.item_date  -> {
				DateViewHolder(ItemDateBinding.inflate(inflater, parent, false))
			}

			else                -> throw IllegalArgumentException("Unknown viewType: $viewType")
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val item = data[position]
		when (holder) {
			is ImageViewHolder -> {
				val photoItem = item as ListItem.PhotoItem
				holder.bind(photoItem.photo, photoItem.date, toImage)
			}

			is DateViewHolder  -> {
				val dateItem = item as ListItem.DateItem
				holder.bind(dateItem.date)
			}
		}
	}

	override fun getItemCount(): Int = data.size

	override fun getItemViewType(position: Int): Int {
		return when (data[position]) {
			is ListItem.PhotoItem -> R.layout.item_image
			is ListItem.DateItem  -> R.layout.item_date
		}
	}

	class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

		fun bind(image: Bitmap, date: String, toImage: (ImageListEvent.NavigateToImage) -> Unit) {
			binding.imageView.setOnClickListener { toImage(ImageListEvent.NavigateToImage(image, date)) }
			binding.imageView.setImageBitmap(image)
		}
	}

	class DateViewHolder(private val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root) {

		fun bind(date: String) {
			binding.dateTextView.text = date
		}
	}
}
