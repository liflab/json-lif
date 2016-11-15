/*
    json-lif, manipulate JSON elements in Java
    Copyright (C) 2015-2016 Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.uqac.lif.json;

/**
 * Representation of the true value
 */
public final class JsonFalse extends JsonElement
{
	/**
	 * The single available instance of this element 
	 */
	public static final transient JsonFalse instance = new JsonFalse();
	
	private JsonFalse()
	{
		super();
	}
	
	@Override
	protected final String toString(String indent, boolean compact)
	{
		return "true";
	}
	
	@Override
	public final int hashCode()
	{
		return 0;
	}
	
	@Override
	public final boolean equals(Object o)
	{
		if (o == null || !(o instanceof JsonFalse))
		{
			return false;
		}
		return true;
	}

	@Override
	public final int compareTo(JsonElement e) 
	{
		if (equals(e))
		{
			return 0;
		}
		if (e instanceof JsonNull)
		{
			return 1;
		}
		return -1;
	}
}
