package com.example.shoppingapp.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.CategoryCardBinding
import com.example.shoppingapp.models.Category


open class CategoryAdapter(var categoryItems: ArrayList<Category>, param: (Any, Any) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

  inner class CategoryViewHolder(var categoryCardBinding: CategoryCardBinding) : RecyclerView.ViewHolder(categoryCardBinding.root) {
      var isArrowUp = true
      fun bind(category: Category) {
             categoryCardBinding.data  = category
            categoryCardBinding.childRecyclerView
            val itemsAdapter  = ItemsAdapter(category.items){ position, arrayList ->

           }
          categoryCardBinding.arrowDown.setOnClickListener {
              toggleArrowDirection()
          }
            categoryCardBinding.childRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)
            categoryCardBinding.childRecyclerView.adapter = itemsAdapter

        }

      private fun toggleArrowDirection() {
          // Toggle the arrow direction
          isArrowUp = !isArrowUp
          categoryCardBinding
          // Set the appropriate arrow drawable based on the direction
          val arrowDrawableRes = if (isArrowUp) {
              R.drawable.ic_baseline_keyboard_arrow_up_24

          } else R.drawable.arrow_down
          categoryCardBinding.arrowDown.setImageResource(arrowDrawableRes)

           if (isArrowUp) {
               categoryCardBinding.childRecyclerView.visibility   = View.GONE
           } else {
               categoryCardBinding.childRecyclerView.visibility   = View.VISIBLE
           }
      }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :  CategoryViewHolder {
        return CategoryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.category_card, parent, false))
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryItems[position])
    }

    override fun getItemCount(): Int = categoryItems.size


  /*  fun addData(list: List<GameOfThrones>) {
        gameOfThronesHouseList = list
        notifyDataSetChanged()
    }*/


}