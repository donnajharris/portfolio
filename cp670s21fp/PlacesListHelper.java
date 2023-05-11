package ca.wlu.cp670.group2.dinearound.view.util;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import ca.wlu.cp670.group2.dinearound.R;

public class PlacesListHelper {

    private static PlacesListHelper instance = null;

    private PlacesListHelper() {
    }

    public static PlacesListHelper getInstance() {
        if (instance == null) {
            instance = new PlacesListHelper();
        }
        return instance;
    }

    public void displayEmptyListMessage(Fragment fragment, boolean show, boolean favouritesOnly) {

        int messageArea;

        if (favouritesOnly) {
            messageArea = R.id.favourite_places_empty_message;
        } else {
            messageArea = R.id.all_places_status_message_area;
        }

        View message = fragment.requireActivity().findViewById(messageArea);

        if (show) {
            if (favouritesOnly) {
                TextView favourites_list_status_message_heading = fragment.requireActivity().findViewById(R.id.favourites_list_status_message_heading);
                favourites_list_status_message_heading.setText(R.string.favourites_list_empty_heading_text);

                TextView favourites_list_status_paragraph1 = fragment.requireActivity().findViewById(R.id.favourites_list_status_paragraph1);
                favourites_list_status_paragraph1.setText(R.string.favourites_list_empty_instructions_text);

                TextView favourites_list_status_paragraph2 = fragment.requireActivity().findViewById(R.id.favourites_list_status_paragraph2);
                favourites_list_status_paragraph2.setText(R.string.change_filter_instruction_text);
            } else {
                TextView all_places_status_message_heading = fragment.requireActivity().findViewById(R.id.all_places_status_message_heading);
                all_places_status_message_heading.setText(R.string.all_places_list_empty_heading_text);

                TextView all_places_status_paragraph1 = fragment.requireActivity().findViewById(R.id.all_places_status_paragraph1);
                all_places_status_paragraph1.setText(R.string.all_places_list_empty_instructions_text);

                TextView all_places_status_paragraph2 = fragment.requireActivity().findViewById(R.id.all_places_status_paragraph2);
                all_places_status_paragraph2.setText(R.string.change_filter_instruction_text);
            }

            message.setVisibility(View.VISIBLE);

        } else {
            message.setVisibility(View.INVISIBLE);
        }
    }
}
