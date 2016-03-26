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
 * Implementation of a numeric value in a JSON structure
 */
public class JsonNumber extends JsonElement
{
	/**
	 * The number value
	 */
	private Number m_number;
	
	/**
	 * Creates a new JSON number with value 0
	 */
	public JsonNumber()
	{
		this(0);
	}

	/**
	 * Creates a new JSON number from a Java number
	 * @param n The number
	 */
	public JsonNumber(Number n)
	{
		super();
		m_number = n;
	}

	/**
	 * Returns the number value of this JSON number
	 * @return The number
	 */
	public Number numberValue()
	{
		return m_number;
	}

	@Override
	public String toString(String indent, boolean compact)
	{
		return m_number.toString();
	}

	/**
	 * Determines if a string contains a numeric value. This is the
	 * case when the string can be parsed as a number without
	 * throwing an exception.
	 * @param str The string
	 * @return true if it contains a numeric value, false otherwise
	 */
	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}

	/**
	 * Determines if a JSON string contains a numeric value.
	 * @param str The string
	 * @return true if it contains a numeric value, false otherwise
	 */
	public static boolean isNumeric(JsonString str)  
	{
		return isNumeric(str.stringValue());
	}

}
