//package com.example.movies
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.annotation.CallSuper
//import androidx.annotation.LayoutRes
//import androidx.databinding.DataBindingUtil
//import androidx.databinding.ViewDataBinding
//import androidx.fragment.app.Fragment
//import kotlin.reflect.KClass
//
//abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment {
//
//    protected abstract fun viewModelClass(): KClass<VM>
//
//    protected val viewModel: VM by lazyViewModels { viewModelClass() }
//
//    protected var binding: B? = null
//
//    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)
//
//    constructor()
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = DataBindingUtil.inflate(inflater, getLayoutXmlId(), container, false)
//        binding?.setVariable(BR.vm, viewModel)
//        binding?.lifecycleOwner = viewLifecycleOwner
//        return binding?.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.onAttached()
//    }
//
//    abstract fun getLayoutXmlId(): Int
//
//    @CallSuper
//    override fun onDestroyView() {
//        super.onDestroyView()
//        try {
//            viewModel.onDetached()
//        } catch (e: UninitializedPropertyAccessException) {
////            Timber.i("uninitialized viewmodel")
//        }
//        binding = null
//    }
//}