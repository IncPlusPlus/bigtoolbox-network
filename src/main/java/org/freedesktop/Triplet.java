package org.freedesktop;

import org.freedesktop.dbus.annotations.Position;

public final class Triplet<A, B, C> {
	@Position(0)
	public final A a;
	@Position(1)
	public final B b;
	@Position(2)
	public final C c;
	
	public Triplet(A a, B b, C c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
