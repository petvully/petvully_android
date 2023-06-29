package org.e1i4.petvully.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.databinding.FragmentAdoptionSearchBinding
import org.e1i4.petvully.databinding.FragmentDonationSearchBinding

@AndroidEntryPoint
class DonationSearchFragment: BaseFragment<FragmentDonationSearchBinding>(FragmentDonationSearchBinding::inflate){}