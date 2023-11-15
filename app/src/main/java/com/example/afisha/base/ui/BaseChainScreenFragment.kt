package com.example.afisha.base.ui

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.afisha.common.cicerone.ChainHolder
import java.lang.ref.WeakReference

/**
 * Фрагмент, способный хранить цепь навигации для Cicerone
 * Здесь реализованы необходимые методы для хранения экранов в цепи навигации
 */
abstract class BaseChainScreenFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val activity = activity
        if (activity is ChainHolder) {
            (activity as ChainHolder).chain.add(WeakReference<Fragment>(this))
        }
    }

    override fun onDetach() {
        val activity = activity
        if (activity is ChainHolder) {
            val chain = (activity as ChainHolder).chain
            val it = chain.iterator()
            while (it.hasNext()) {
                val fragmentReference = it.next()
                val fragment = fragmentReference.get()
                if (fragment != null && fragment === this) {
                    it.remove()
                    break
                }
            }
        }
        super.onDetach()
    }
}