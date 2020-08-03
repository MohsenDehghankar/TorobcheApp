package com.sharifdev.torobche.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FunctionCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.sharifdev.torobche.Category.SelectCategoryActivity;
import com.sharifdev.torobche.R;
import com.sharifdev.torobche.backUtils.HistoryUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {
    private ArrayList<HistoryAdapter.HistoryValue> histories = new ArrayList<>();
    private ArrayList<SelectCategoryActivity.HolderClass> quizzes = new ArrayList<>();
    private ArrayList<SelectCategoryActivity.HolderClass> questions = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity, container, false);

        initHistoryRecyclerView(rootView);

        initQuizRecyclerView(rootView);

        initQuestionRecyclerView(rootView);

        return rootView;
    }

    private void initHistoryRecyclerView(View rootView) {
        final RecyclerView historyRecyclerView = rootView.findViewById(R.id.history_recyclerView);
        historyRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        historyRecyclerView.setLayoutManager(layoutManager);

        HistoryUtils.getUserHistory(new FunctionCallback<List<ParseObject>>() {
            @Override
            public void done(List<ParseObject> object, ParseException e) {
                histories = new ArrayList<>();
                if (e != null)
                    e.printStackTrace();
                else {
                    for (ParseObject parseObject : object) {
                        histories.add(new HistoryAdapter.HistoryValue(
                                parseObject.getInt("point"),
                                new SimpleDateFormat("dd-MM-yyyy").format(parseObject.getDate("quizDate")),
                                ((String) parseObject.get("categoryName"))
                        ));
                    }
                }
                HistoryAdapter mAdapter = new HistoryAdapter(getContext(), histories);
                historyRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    private void initQuizRecyclerView(View rootView) {
        RecyclerView quizRecyclerView = rootView.findViewById(R.id.quiz_recyclerView);
        quizRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        quizRecyclerView.setLayoutManager(layoutManager);

        // todo get all quizzes from server
        for (int i = 0; i < 10; i++)
            quizzes.add(new SelectCategoryActivity.HolderClass("quiz" + i, R.drawable.quiz));

        QuizAdapter mAdapter = new QuizAdapter(getContext(), quizzes);
        quizRecyclerView.setAdapter(mAdapter);
    }

    private void initQuestionRecyclerView(View rootView) {
        RecyclerView questionRecyclerView = rootView.findViewById(R.id.question_recyclerView);

        questionRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        questionRecyclerView.setLayoutManager(layoutManager);

        // todo
        for (int i = 0; i < 10; i++)
            questions.add(new SelectCategoryActivity.HolderClass("question" + i, R.drawable.question));

        QuestionAdapter mAdapter = new QuestionAdapter(getContext(), questions);
        questionRecyclerView.setAdapter(mAdapter);
    }

}
