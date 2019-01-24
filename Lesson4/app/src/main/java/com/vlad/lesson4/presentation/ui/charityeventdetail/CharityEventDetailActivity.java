package com.vlad.lesson4.presentation.ui.charityeventdetail;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.vlad.lesson4.R;
import com.vlad.lesson4.data.model.CharityEvent;
import com.vlad.lesson4.data.model.Event;
import com.vlad.lesson4.presentation.ui.base.BaseActivity;
import com.vlad.lesson4.utils.Date;
import com.vlad.lesson4.utils.MakeLinksClickable;
import com.vlad.lesson4.utils.MyGlide;

import org.threeten.bp.LocalDate;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;

import static com.vlad.lesson4.presentation.ui.main.MainActivity.DOT;
import static com.vlad.lesson4.presentation.ui.main.MainActivity.EMPTY;
import static com.vlad.lesson4.presentation.ui.main.MainActivity.NOTHING;

public class CharityEventDetailActivity extends BaseActivity implements CharityEventDetailMvpView {

    public static final String EXTRA_ID_EVENT = "EXTRA_ID_EVENT";
    public static final int DEFAULT_VALUE = -1;
    public static final int STATUS_FINISH = -1;
    public static final int STATUS_NOT_SHOW = -2;

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;


    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private CharityEventDetailPresenter charityEventDetailPresenter;
    private TextView textViewTitleDetailEvent;
    private TextView textViewTimeDetailEvent;
    private TextView textViewNameCompanyEvent;
    private TextView textViewAddressCompany;
    private TextView textViewNumberPhoneCompany;
    private ImageView imageViewOne;
    private ImageView imageViewTwo;
    private ImageView imageViewThree;
    private TextView textViewTextEvent;
    private TextView textViewSiteEvent;
    private TextView textViewAskEvent;
    private LinearLayout linearLayoutImageEvent;

    public static Intent createStartIntent(Context context, int idEvent) {
        Intent intent = new Intent(context, CharityEventDetailActivity.class);
        intent.putExtra(EXTRA_ID_EVENT, idEvent);
        return intent;
    }

    public static String getStatus(long days, Event event, Resources res) {
        LocalDate localDate = LocalDate.now();
        if (days == STATUS_FINISH) {
            return res.getString(R.string.completed) + EMPTY + event.getTimeFinish();
        } else if (days == STATUS_NOT_SHOW) {
            return res.getString(R.string.will_begin) + EMPTY + event.getTimeStart();
        } else {
            return String.format(res.getQuantityString(R.plurals.days_left, (int) days),
                    days,
                    event.getTimeStart().replace(String.valueOf(DOT + localDate.getYear()), NOTHING),
                    event.getTimeFinish().replace(String.valueOf(DOT + localDate.getYear()), NOTHING)
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_event_detail);

        toolbar = findViewById(R.id.toolbarEventDetail);
        viewFlipper = findViewById(R.id.viewFlipperEventDetail);
        textViewTitleDetailEvent = findViewById(R.id.textViewTitleDetailEvent);
        textViewAddressCompany = findViewById(R.id.textViewAddressCompany);
        textViewNameCompanyEvent = findViewById(R.id.textViewNameCompanyEvent);
        textViewTimeDetailEvent = findViewById(R.id.textViewTimeDetailEvent);
        textViewNumberPhoneCompany = findViewById(R.id.textViewNumberPhoneCompany);
        imageViewOne = findViewById(R.id.imageViewOne);
        imageViewTwo = findViewById(R.id.imageViewTwo);
        imageViewThree = findViewById(R.id.imageViewThree);
        textViewTextEvent = findViewById(R.id.textViewTextEvent);
        linearLayoutImageEvent = findViewById(R.id.linearLayoutImageEvent);
        textViewSiteEvent = findViewById(R.id.textViewSiteEvent);
        textViewAskEvent = findViewById(R.id.textViewAskEvent);

        toolbar.setTitle(EMPTY);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        charityEventDetailPresenter = getApplicationComponents().provideCharityEventDetailPresenter();
        charityEventDetailPresenter.attachView(this);
        int id = getIntent().getIntExtra(EXTRA_ID_EVENT, DEFAULT_VALUE);
        charityEventDetailPresenter.onCreate(getApplicationContext(), id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        menu.findItem(R.id.edit_profile).setVisible(false);
        menu.findItem(R.id.event_filter).setVisible(false);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showLoadingError() {
        viewFlipper.setDisplayedChild(VIEW_ERROR);
    }

    @Override
    public void showProgressView() {
        viewFlipper.setDisplayedChild(VIEW_LOADING);
    }

    @Override
    public Event getEvent(CharityEvent charityEvent, int id) {

        if (charityEvent != null && id != DEFAULT_VALUE) {
            for (Event event : charityEvent.getEvents()) {
                if (event.getId() == id) {
                    return event;
                }
            }
        }
        return null;
    }

    @Override
    public void showEventDetail(Event event) {
        viewFlipper.setDisplayedChild(VIEW_DATA);
        Objects.requireNonNull(getSupportActionBar()).setTitle(event.getName());
        textViewTitleDetailEvent.setText(event.getName());
        textViewAddressCompany.setText(event.getAddressCompany());
        textViewNameCompanyEvent.setText(event.getCharityCompany());
        long days = Date.getDays(event.getTimeStart(), event.getTimeFinish());
        textViewTimeDetailEvent.setText(getStatus(days, event, getResources()));
        textViewNumberPhoneCompany.setText(getNumbers(event));
        setImageExtra(event);
        textViewTextEvent.setText(event.getText());
        setLinksOnTextView(textViewSiteEvent, event.getUrlSiteCompany());
        setLinksOnTextView(textViewAskEvent, event.getEmailCompany());
    }

    private StringBuilder getNumbers(Event event) {
        StringBuilder numbers = new StringBuilder();
        int sizeNumbers = event.getNumberPhone().size();
        for (int i = 0; i < sizeNumbers; i++) {
            if (i == sizeNumbers - 1) {
                numbers.append(event.getNumberPhone().get(i).getNumberCompany());
            } else {
                numbers.append(event.getNumberPhone().get(i).getNumberCompany()).append("\n");
            }
        }
        return numbers;
    }

    private void setImageExtra(Event event) {
        int sizeImage = event.getImageExtra().size();
        if (sizeImage == 0) {
            linearLayoutImageEvent.setVisibility(View.GONE);
        } else {
            for (int positionImage = 0; positionImage < sizeImage; positionImage++) {
                MyGlide.loadImage(getApplicationContext(),
                        event.getImageExtra().get(positionImage).getImage(),
                        positionImage, imageViewOne, imageViewTwo, imageViewThree);
            }
        }
    }

    private void setLinksOnTextView(TextView textView, String url) {
        String textWithLink;
        if (textView == textViewAskEvent) {
            textWithLink = String.format(getString(R.string.write_us_link), url,
                    getString(R.string.write_us));
        } else {
            textWithLink = String.format(getString(R.string.go_to_site_organization_link), url,
                    getString(R.string.go_to_site_organization));
        }
        textView.setText(Html.fromHtml(textWithLink));
        textView.setLinksClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            textView.setText(MakeLinksClickable.reformatText(text));
        }
    }
}


