package ptk111.com.jonqko;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private int[] layouts;
    private LinearLayout dotsLayout;
    private TextView[] dotsHome;

    private TextView tvAlamat;
    private Button btGantiAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbarHome);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle(null);

        viewPager = (ViewPager)findViewById(R.id.view_pager_home);
        dotsLayout = (LinearLayout)findViewById(R.id.layoutDotsHome);

        /*--- Linear Layout Alamat ---*/
        tvAlamat = (TextView)findViewById(R.id.textviewAlamat);
        btGantiAlamat = (Button)findViewById(R.id.buttonGantiAlamat);
        btGantiAlamat.setTransformationMethod(null);

        btGantiAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast. makeText (getApplicationContext(), "Ganti Alamat" ,
                        Toast. LENGTH_LONG ).show();
            }
        });


        layouts = new int[]{R.layout.slider_home1,R.layout.slider_home2};
        addBottomDots(0);
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_top_toolbar,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menuSrch:
                Toast. makeText (getApplicationContext(), "Search" ,
                        Toast. LENGTH_LONG ).show();
                return true ;
            case R.id.menuCart:
                Toast. makeText (getApplicationContext(), "Cart" ,
                        Toast. LENGTH_LONG ).show();
                return true ;
            case R.id.tabItem:
                Toast. makeText(getApplicationContext(), "Item" ,
                        Toast. LENGTH_LONG).show();
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    private void addBottomDots(int position){
        dotsHome = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for(int i = 0; i < dotsHome.length; i++){
            dotsHome[i] = new TextView(this);
            dotsHome[i].setText(Html.fromHtml("&#8226;"));
            dotsHome[i].setTextSize(35);
            dotsHome[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dotsHome[i]);
        }
        if (dotsHome.length > 0) {
            dotsHome[position].setTextColor(colorActive[position]);
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public class ViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View)object;
            container.removeView(v);
        }
    }

}
