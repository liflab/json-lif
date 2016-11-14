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

import org.json.simple.JSONValue;

/**
 * Implementation of a string value in a JSON structure
 */
public class JsonString extends JsonElement
{
	/**
	 * The string value
	 */
	private String m_string;
	
	/**
	 * Creates a new empty JSON string
	 */
	public JsonString()
	{
		super();
		m_string = "";
	}

	/**
	 * Creates a new JSON string from a Java string
	 * @param s The string
	 */
	public JsonString(String s)
	{
		super();
		m_string = s;
	}

	/**
	 * Returns the string value of this JSON string
	 * @return The string
	 */
	public String stringValue()
	{
		return m_string;
	}

	@Override
	public String toString(String indent, boolean compact)
	{
		StringBuilder out = new StringBuilder();
		out.append("\"").append(JSONValue.escape(m_string)).append("\"");
		return out.toString();
	}
	
	@Override
	public int hashCode()
	{
		return m_string.hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o == null || !(o instanceof JsonString))
		{
			return false;
		}
		if (o == this)
		{
			return true;
		}
		return m_string.compareTo(((JsonString) o).m_string) == 0;
	}

}
