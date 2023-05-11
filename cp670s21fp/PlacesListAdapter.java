package ca.wlu.cp670.group2.dinearound.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.wlu.cp670.group2.dinearound.R;
import ca.wlu.cp670.group2.dinearound.model.DayOfWeek;
import ca.wlu.cp670.group2.dinearound.model.Place;
import ca.wlu.cp670.group2.dinearound.model.PlaceDayHour;
import ca.wlu.cp670.group2.dinearound.view.util.PlaceDayHourHelper;
import ca.wlu.cp670.group2.dinearound.view.util.PlacesListHelper;
import ca.wlu.cp670.group2.dinearound.view.util.TimeHelper;

public class PlacesListAdapter extends BaseAdapter {

    private final Context context;
    private final Fragment fragment;
    private final static int MAX_DISTANCE_TO_DISPLAY = 120; // in km
    private final List<Place> places;
    private final boolean favouritesOnly;

    private final PlacesListHelper helper = PlacesListHelper.getInstance();

    private final static int NO_DISTANCE = -1;

    public PlacesListAdapter(Context context, Fragment fragment, boolean isFavouritesOnly) {
        this.context = context;
        this.fragment = fragment;
        places = new ArrayList<>();
        favouritesOnly = isFavouritesOnly;
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Place getItem(int position) {
        return places.get(position);
    }

    @Override
    public long getItemId(int position) {
        return places.get(position).getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Place place = places.get(position);
        View placeItemView = createView(parent);
        bindViewWithFields(place, placeItemView);
        return placeItemView;
    }

    public void update(List<Place> places) {
        this.places.clear();
        this.places.addAll(places);
        notifyDataSetChanged();
        checkForEmptyList();
    }

    public void remove(Place place) {
        places.remove(place);
        notifyDataSetChanged();
        checkForEmptyList();
    }

    private void checkForEmptyList() {
        if (places.isEmpty()) {
            helper.displayEmptyListMessage(fragment, true, favouritesOnly);
        }
    }

    private View createView(ViewGroup parent) {

        return LayoutInflater
                .from(context)
                .inflate(R.layout.place_item, parent, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void bindViewWithFields(Place place, View view) {

        helper.displayEmptyListMessage(fragment, false, favouritesOnly);

        ImageView placeImage = view.findViewById(R.id.place_list_photo);
        configurePlaceImage(place, placeImage);

        TextView businessName = view.findViewById(R.id.place_list_business_name);
        configureBusinessName(place, businessName);

        TextView placeCategory = view.findViewById(R.id.place_list_category);
        configureCategoryView(place, placeCategory);

        RatingBar placeRatingBar = view.findViewById(R.id.place_list_rating_bar);
        TextView notRatedLabel = view.findViewById(R.id.place_list_no_rating);
        configureRatingBar(place, placeRatingBar, notRatedLabel);

        TextView placeAddressString = view.findViewById(R.id.place_list_address_string);
        configureAddressString(place, placeAddressString);

        TextView placeStatusDot = view.findViewById(R.id.place_list_status_dot);
        TextView placeStatusMarker = view.findViewById(R.id.place_list_status);
        configureStatus(place, placeStatusMarker, placeStatusDot);

        ImageView favouriteMarker = view.findViewById(R.id.favourite_marker);
        configureFavouriteMarker(place, favouriteMarker);

        // MOCKED...
        TextView placeDistanceFromDevice = view.findViewById(R.id.place_list_distance);
        configureDistance(place, placeDistanceFromDevice);
    }

    private void configurePlaceImage(Place place, ImageView placeImage) {

        if (place.getImage() != null) {
            placeImage.setImageBitmap(place.getImage());
        } else {
            placeImage.setImageDrawable(
                    AppCompatResources.getDrawable(context,
                            R.drawable.dine_around_grayscale_icon));
        }

        placeImage.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_main_image));
        placeImage.setClipToOutline(true);
    }

    private void configureBusinessName(Place place, TextView businessName) {
        businessName.setText(place.getBusinessName());
    }

    @SuppressLint("NonConstantResourceId")
    private void configureCategoryView(Place place, TextView placeCategory) {

        switch (place.getCategory()) {
            case R.id.coffee_shops:
                placeCategory.setText(R.string.coffee_shops);
                placeCategory.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_category_coffee_shops));
                break;
            case R.id.restaurants:
                placeCategory.setText(R.string.restaurants);
                placeCategory.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_category_restaurants));
                break;
            case R.id.bars:
                placeCategory.setText(R.string.bars);
                placeCategory.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_category_bars));
                break;
            default:
                placeCategory.setMaxHeight(0);
                placeCategory.setMaxWidth(0);
        }
    }

    private void configureRatingBar(Place place, RatingBar placeRatingBar, TextView ratingTextInfoLabel) {

        float ratingValue = place.getRating();
        placeRatingBar.setVisibility(View.VISIBLE);

        if (ratingValue == Place.NO_RATING) { // example of UNRATED place
            ratingTextInfoLabel.setVisibility(View.VISIBLE);
            ratingTextInfoLabel.setTextColor(context.getColor(R.color.unrated_text_color));
            ratingTextInfoLabel.setBackgroundColor(context.getColor(R.color.transparent_white));
            ratingTextInfoLabel.setText(context.getString(R.string.unrated_label));

        } else if (ratingValue == Place.ZERO_RATING) { // example of Bad ZERO rating UI
            placeRatingBar.setRating(ratingValue);
            ratingTextInfoLabel.setTextColor(context.getColor(R.color.avoid_text_color));
            ratingTextInfoLabel.setBackgroundColor(context.getColor(R.color.transparent_white));
            ratingTextInfoLabel.setText(context.getString(R.string.avoid_label));

        } else {
            ratingTextInfoLabel.setVisibility(View.INVISIBLE);
            placeRatingBar.setRating(ratingValue);
        }
    }

    private void configureAddressString(Place place, TextView placeAddressString) {
        String concatenatedAddressValues = createConcatenatedAddress(place);
        placeAddressString.setText(concatenatedAddressValues);

        if (concatenatedAddressValues.isEmpty())
            placeAddressString.setMaxHeight(0);
    }

    private String createConcatenatedAddress(Place place) {
        String value = "";

        if (place.getAddress() != null && !place.getAddress().isEmpty()) {
            value += place.getAddress() + "\n";
        }

        if (place.getCity() != null && !place.getCity().isEmpty()) {
            value += place.getCity();
        }

        if (place.getProvince() != null && !place.getProvince().isEmpty()) {
            if (!value.isEmpty() && place.getCity() != null && !place.getCity().isEmpty())
                value += ", ";

            value += place.getProvince();
        }

        return value;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void configureStatus(Place place, TextView placeStatus, TextView placeStatusDot) {
        String status = getCurrentStatus(place);

        switch (status) {
            case Place.HAPPY_HOUR_STATUS:
                placeStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_status_label_happy));
                placeStatus.setTextColor(ContextCompat.getColor(context, R.color.happy_hour_color));
                placeStatus.setText(context.getResources().getString(R.string.status_happy_hour));
                placeStatusDot.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_status_dot_happy));
                break;
            case Place.OPEN_STATUS:
                placeStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_status_label_open));
                placeStatus.setTextColor(ContextCompat.getColor(context, R.color.open_color));
                placeStatus.setText(context.getResources().getString(R.string.status_open));
                placeStatusDot.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_status_dot_open));
                break;
            case Place.CLOSING_SOON_STATUS:
                placeStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_status_label_closing));
                placeStatus.setTextColor(ContextCompat.getColor(context, R.color.closing_soon_color));
                placeStatus.setText(context.getResources().getString(R.string.status_closing_soon));
                placeStatusDot.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_status_dot_closing));
                break;
            case Place.CLOSED_STATUS:
                placeStatus.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_status_label_closed));
                placeStatus.setTextColor(ContextCompat.getColor(context, R.color.closed_color));
                placeStatus.setText(context.getResources().getString(R.string.status_closed));
                placeStatusDot.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_rounded_status_dot_closed));
                break;
            default:
                // No status available to display (because there are no hours for the place, or they cannot be determined)
                placeStatus.setVisibility(View.INVISIBLE);
                placeStatusDot.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getCurrentStatus(Place place) {

        String currentDayOfTheWeek = TimeHelper.getInstance().getCurrentDayOfTheWeek();
        DayOfWeek today = DayOfWeek.valueOf(currentDayOfTheWeek);

        PlaceDayHourHelper helper = PlaceDayHourHelper.getInstance();
        List<PlaceDayHour> filteredPlaceDayHourListForToday = helper.filterPlaceDayHourListByDay(place.getPlaceDayHourList(), today);

        if (helper.hasStartAndEndHours(filteredPlaceDayHourListForToday)) {
            if (helper.happyHourIsNow(filteredPlaceDayHourListForToday, TimeHelper.getInstance().getCurrentTime())) {
                return Place.HAPPY_HOUR_STATUS;
            } else if (helper.isClosingSoon(filteredPlaceDayHourListForToday, TimeHelper.getInstance().getCurrentTime())) {
                return Place.CLOSING_SOON_STATUS;
            } else if (helper.isOpenNow(filteredPlaceDayHourListForToday, TimeHelper.getInstance().getCurrentTime())) {
                return Place.OPEN_STATUS;
            } else {
                return Place.CLOSED_STATUS;
            }
        } else if (helper.hasStartAndEndHours(place.getPlaceDayHourList())) {
            // place has hours on other days of the week, so it is closed
            return Place.CLOSED_STATUS;
        }

        return Place.HAS_NO_STATUS;
    }

    private void configureFavouriteMarker(Place place, ImageView favouriteMarker) {

        if (favouritesOnly) {
            favouriteMarker.setVisibility(View.INVISIBLE);
        } else if (place.isFavorite()) {
            favouriteMarker.setVisibility(View.VISIBLE);
            favouriteMarker.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_outline_favourite_24));
            DrawableCompat.setTint(favouriteMarker.getDrawable().mutate(), ContextCompat.getColor(context, R.color.favourite_fill_color));
        }
        // else, relying on the layout to show the grey outline... making invisible
        else {
            favouriteMarker.setVisibility(View.INVISIBLE);
        }
    }


    // MOCKED DISTANCES
    private void configureDistance(Place place, TextView placeDistanceFromDevice) {
        double calculatedDistance = getMockedDistanceFromWaterloo(place);

        if (calculatedDistance == NO_DISTANCE) {
            placeDistanceFromDevice.setVisibility(View.INVISIBLE);  // handling no distance available
        } else if (calculatedDistance < MAX_DISTANCE_TO_DISPLAY) {
            String distanceString = calculatedDistance + " km";
            placeDistanceFromDevice.setText(distanceString);
        } else {
            placeDistanceFromDevice.setVisibility(View.INVISIBLE);  // handling too far to share
        }
    }

    private double getMockedDistanceFromWaterloo(Place place) {

        Map<String, Double> approximateDistanceFromWaterloo = new HashMap<>();
        approximateDistanceFromWaterloo.put("Waterloo", 0.5);
        approximateDistanceFromWaterloo.put("Kitchener", 3.6);
        approximateDistanceFromWaterloo.put("Guelph", 28.1);
        approximateDistanceFromWaterloo.put("Elora", 31.0);
        approximateDistanceFromWaterloo.put("Burlington", 74.3);
        approximateDistanceFromWaterloo.put("Toronto", 112.6);
        approximateDistanceFromWaterloo.put("London", 113.4);
        approximateDistanceFromWaterloo.put("Sudbury", 462.8);
        approximateDistanceFromWaterloo.put("Surrey", 4103.1);
        approximateDistanceFromWaterloo.put("Vancouver", 4128.3);

        if (approximateDistanceFromWaterloo.containsKey(place.getCity())) {
            Double value = approximateDistanceFromWaterloo.get(place.getCity());
            if (value == null) {
                return NO_DISTANCE;
            } else {
                return value;
            }
        } else {
            return NO_DISTANCE;
        }
    }

    public List<Place> getList() {
        return places;
    }
}
