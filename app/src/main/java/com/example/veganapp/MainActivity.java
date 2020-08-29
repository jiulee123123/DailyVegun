package com.example.veganapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
/*
    int images[] = {R.drawable.product_1, R.drawable.product_2, R.drawable.product_3, R.drawable.product_4,
    R.drawable.product_5, R.drawable.product_6, R.drawable.product_7, R.drawable.product_8,
    R.drawable.product_9, R.drawable.product_10,R.drawable.product_11,R.drawable.product_12,
            R.drawable.product_13, R.drawable.product_14, R.drawable.product_15,R.drawable.product_16,
            R.drawable.product_17, R.drawable.product_18, R.drawable.product_19, R.drawable.product_20,
            R.drawable.product_21, R.drawable.product_22, R.drawable.product_23, R.drawable.product_24,
            R.drawable.product_26, R.drawable.product_27};
    String names[] = {"[오뚜기] 채황", "[삼양] 채식라면", "[농심] 야채라면", "[삼양] 불타는 고추라면",
    "[풀무원] 정면","[팔도] 비빔장", "[오뚜기] 피자소스", "[CJ제일제당] 사과듬뿍 비빔장",
    "[백설] 매콤한 돼지 불고기 양념", "[샘표] 비빔국수","[대림사조] 채담만두", "[Lembeke] 로투스 비스코프",
    "[롯데] 꼬깔콘 고소한맛", "[오리온] 통크 피넛", "[노브랜드] 감자칩 오리지널","[네슬레] 프루팁스",
    "[삼육] 김치볶음밥", "[피코크] 구운사리면", "[매일유업] 매일두유", "[매일유업] 매일두유 식이섬유",
    "[매일유업] 매일두유 초콜릿","[매일유업] 아몬드 브리즈 오리지널", "[매일유업] 아몬드 브리즈 언스위트", "[매일유업] 아몬드 브리즈 바나나",
    "[매일유업] 아몬드 브리즈 초콜릿", "[키토제니] 아몬드 블랙티", "[노브랜드] 무농약 현미 스틱 미숫가루"};
    String desc[] = {"라면" , "라면" , "라면" , "라면",
     "라면", "양념" , "양념" , "양념" ,
     "양념" , "간편식", "간편식", "과자",
     "과자", "과자", "과자", "과자",
     "간편식", "간편식", "음료", "음료",
     "음료", "음료", "음료", "음료",
     "음료", "음료", "음료"};
*/
    int images[] ={R.drawable.product_1, R.drawable.product_2, R.drawable.product_3, R.drawable.product_4,
        R.drawable.product_5, R.drawable.product_6, R.drawable.product_7, R.drawable.product_8,
        R.drawable.product_9, R.drawable.product_10,R.drawable.product_11,R.drawable.product_12,
        R.drawable.product_13, R.drawable.product_14};
    String names[] ={"[오뚜기] 채황", "[삼양] 채식라면", "[농심] 야채라면", "[삼양] 불타는 고추라면",
            "[풀무원] 정면", "[팔도] 비빔장", "[오뚜기] 피자소스", "[CJ제일제당] 사과듬뿍 비빔장",
            "[백설] 매콤한 돼지 불고기 양념", "[샘표] 비빔국수","[대림사조] 채담만두", "[Lembeke] 로투스 비스코프",
            "[롯데] 꼬깔콘 고소한맛", "[오리온] 통크 피넛"};
    String desc[]={"라면" , "라면" , "라면" , "라면",
            "라면", "양념" , "양념" , "양념",
            "양념" , "간편식", "간편식", "과자",
            "과자", "과자"};

    List<ItemsModel> listItems = new ArrayList<>();

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = findViewById(R.id.listview);

        for(int i = 0; i < names.length; i++){
            ItemsModel itemsModel = new ItemsModel(names[i], desc[i], images[i]);

            listItems.add(itemsModel);
        }

        customAdapter = new CustomAdapter(listItems, this );

        listView.setAdapter(customAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                customAdapter.getFilter().filter(newText);

                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.search_view) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<ItemsModel> itemsModelList;
        private List<ItemsModel> itemsModelListFiltered;
        private Context context;

        public CustomAdapter(List<ItemsModel> itemsModelList, Context context) {
            this.itemsModelList = itemsModelList;
            this.itemsModelListFiltered = itemsModelList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return itemsModelListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.row_items, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView itemName = view.findViewById(R.id.itemName);
            TextView itemDesc = view.findViewById(R.id.itemDesc);

            imageView.setImageResource(itemsModelListFiltered.get(position).getImage());
            itemName.setText(itemsModelListFiltered.get(position).getName());
            itemDesc.setText(itemsModelListFiltered.get(position).getDesc());



            return view;
        }

        @Override
        public Filter getFilter() {
            final Filter filter = new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();

                    if(constraint == null || constraint.length() == 0 ){
                        filterResults.count = itemsModelList.size();
                        filterResults.values = itemsModelList;

                    }else{

                        String searchStr = constraint.toString().toLowerCase();

                        List<ItemsModel> resultData = new ArrayList<>();

                        for(ItemsModel itemsModel:itemsModelList){
                            if(itemsModel.getName().contains(searchStr) || itemsModel.getDesc().contains(searchStr)){
                                resultData.add(itemsModel);
                            }

                            filterResults.count = resultData.size();
                            filterResults.values = resultData;
                        }

                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    itemsModelListFiltered = (List<ItemsModel>) results.values;

                    notifyDataSetChanged();

                }
            };


            return filter;
        }
    }
}
