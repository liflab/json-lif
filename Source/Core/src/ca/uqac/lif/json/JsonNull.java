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
 * Representation of the null value
 */
public class JsonNull extends JsonElement
{
	public JsonNull()
	{
		super();
	}
	
	@Override
	protected String toString(String indent, boolean compact)
	{
		return "null";
	}
	
	@Override
	public int hashCode()
	{
		return -1;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o == null || !(o instanceof JsonNull))
		{
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(JsonElement o) 
	{
		return -1;
	}
}
