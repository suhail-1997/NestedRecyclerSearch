package com.example.nestedrecyclersearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView menuRecycler;
    private List<CacheMenuRes> cacheMenuRes;
    private MainMenuAdapter mainMenuAdapter;
    private ImageView search;
    private EditText searchText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuRecycler = findViewById(R.id.menurecycler);
        search = findViewById(R.id.search);
        searchText = findViewById(R.id.searchbox);
        progressBar = findViewById(R.id.pbar);
        searchText.setVisibility(View.GONE);
        cacheMenuRes = new ArrayList<>();
        final float scale = getResources().getDisplayMetrics().density;
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchText.getVisibility() == View.VISIBLE)
                {
                    searchText.setVisibility(View.GONE);
                    menuRecycler.setPadding(0,0,0,0);
                }
                else
                {
                    searchText.setVisibility(View.VISIBLE);
                    menuRecycler.setPadding(0,(int) (50*scale + 0.5f),0,0);
                }
            }
        });
        cacheMenuRes = new ArrayList<>();
        mainMenuAdapter = new MainMenuAdapter(MainActivity.this,cacheMenuRes);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        menuRecycler.setLayoutManager(layoutManager);
        menuRecycler.setItemViewCacheSize(20);
        menuRecycler.setAdapter(mainMenuAdapter);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchedVal = s.toString();

                String categoryString = "";
                List<CacheMenuRes> cacheMenuResList = new ArrayList<>();
                List<Menu> ms = new ArrayList<>();
                if(TextUtils.isEmpty(searchedVal))
                {
                    mainMenuAdapter.setCacheMenuRes(cacheMenuRes);

                }
                else
                {
                    for(CacheMenuRes c : cacheMenuRes)
                    {
                        List<Menu> menuList = c.getMenus();

                        for(Menu m : menuList)
                        {
                            if(m.getName().contains(searchedVal))
                            {
                                categoryString = c.getSubCategoryName();
                                ms.add(m);
                            }
                        }

                    }
                    cacheMenuResList.add(new CacheMenuRes(categoryString,ms));

                    mainMenuAdapter.setCacheMenuRes(cacheMenuResList);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getMenu();
    }
    public void getMenu()
    {
        progressBar.setVisibility(View.VISIBLE);
        String url = "http://conceptgrammarschool.com:3001/menu";
        //http://162.144.80.175:3001/menu
        final HashMap<String,List<Menu>> map = new HashMap<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("message").equals("success"))
                    {
                        //response object
//                        {
//                            "data": [
//                            {
//                                "subcategoryid": 1,
//                                    "categoryname": "starter",
//                                    "menuid": 1,
//                                    "name": "idli",
//                                    "price": 50,
//                                    "type": "veg"
//                            },
//                            {
//                                "subcategoryid": 1,
//                                    "categoryname": "starter",
//                                    "menuid": 2,
//                                    "name": "upma",
//                                    "price": 30,
//                                    "type": "veg"
//                            },
//                            {
//                                "subcategoryid": 2,
//                                    "categoryname": "liquids",
//                                    "menuid": 3,
//                                    "name": "lemon juice",
//                                    "price": 10,
//                                    "type": "veg"
//                            },
//                            {
//                                "subcategoryid": 2,
//                                    "categoryname": "liquids",
//                                    "menuid": 4,
//                                    "name": "tea",
//                                    "price": 30,
//                                    "type": "veg"
//                            },
//                            {
//                                "subcategoryid": 3,
//                                    "categoryname": "main course",
//                                    "menuid": 5,
//                                    "name": "dosa",
//                                    "price": 25,
//                                    "type": "veg"
//                            },
//                            {
//                                "subcategoryid": 3,
//                                    "categoryname": "main course",
//                                    "menuid": 6,
//                                    "name": "masala dosa",
//                                    "price": 30,
//                                    "type": "veg"
//                            },
//                            {
//                                "subcategoryid": 4,
//                                    "categoryname": "desert",
//                                    "menuid": 7,
//                                    "name": "curd",
//                                    "price": 10,
//                                    "type": "veg"
//                            },
//                            {
//                                "subcategoryid": 4,
//                                    "categoryname": "desert",
//                                    "menuid": 8,
//                                    "name": "lassi",
//                                    "price": 20,
//                                    "type": "veg"
//                            }
//                            ],
//                            "fullError": null,
//                                "message": "success"
//                        }
                        JSONArray jsonArray = response.getJSONArray("data");
                        for(int i=0 ;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject =jsonArray.getJSONObject(i);
                            if(map.containsKey(jsonObject.getString("categoryname")))
                            {
                                List<Menu> menu = map.get(jsonObject.getString("categoryname"));
                                menu.add(new Menu(jsonObject.getInt("menuid"),jsonObject.getInt("subcategoryid"),jsonObject.getString("name"),jsonObject.getDouble("price"),jsonObject.getString("type")));
                                map.put(jsonObject.getString("categoryname"),menu);
                            }
                            else
                            {
                                List<Menu> menus = new ArrayList<>();
                                menus.add(new Menu(jsonObject.getInt("menuid"),jsonObject.getInt("subcategoryid"),jsonObject.getString("name"),jsonObject.getDouble("price"),jsonObject.getString("type")));
                                map.put(jsonObject.getString("categoryname"),menus);
                            }

                        }
                        for(String catname : map.keySet())
                        {
                            cacheMenuRes.add(new CacheMenuRes(catname,map.get(catname)));
                        }
                        progressBar.setVisibility(View.GONE);
                        mainMenuAdapter.notifyDataSetChanged();

                    }
                    else
                    {
                        Log.i("menu res",response.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
    }
}
