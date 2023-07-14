package ecs.components

import com.badlogic.ashley.core.Entity

class ZOrderComparator : Comparator<Entity> {

    companion object {
        val comparator : ZOrderComparator = ZOrderComparator()
    }

    override fun compare(p0: Entity?, p1: Entity?): Int {
        return ZOrderComponent.ZORDERMAPPER[p0].zAxis.compareTo(ZOrderComponent.ZORDERMAPPER[p1].zAxis)
    }

}
