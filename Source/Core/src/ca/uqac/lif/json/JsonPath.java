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

import java.util.LinkedList;
import java.util.List;

/**
 * Representation of a path in a JSON element. A path is used to fetch
 * a part of a JSON element, and follows the syntax used in JavaScript
 * to query object contents.
 * <p>
 * For example, consider the following JSON element:
 * <pre>
 * {
 *   "a" : 0,
 *   "b" : [
 *     {
 *       "c" : 3
 *     },
 *     {
 *       "c" : 2
 *     },
 *   ],
 *   "d" : [1,2,3]
 * }
 * </pre>
 * then the path <tt>b[1].c</tt> corresponds to the number <tt>2</tt>,
 * and the path <tt>d</tt> corresponds to the list <tt>[1,2,3]</tt>.
 */
public class JsonPath
{
	/**
	 * Retrieves part of a JSON element based on a path
	 * @param root The element to look into
	 * @param path A string representing the path to follow
	 * @return A JSON element corresponding to the end of the path,
	 *   or null if the path does not correspond to anything in
	 *   the JSON element
	 */
	public static JsonElement get(JsonElement root, String path)
	{
		return get(root, getPathElements(path));
	}
	
	/**
	 * Parses a string to a list of path elements
	 * @param path A string representing the path to follow
	 * @return A JSON element corresponding to the end of the path,
	 *   or null if the path does not correspond to anything in
	 *   the JSON element
	 */
	public static List<PathElement> getPathElements(String path)
	{
		path = path.replace(".", "/");
		path = path.replace("[", "/[");
		String[] parts = path.split("/");
		LinkedList<PathElement> out_path = new LinkedList<PathElement>();
		for (String part : parts)
		{
			if (part.startsWith("["))
			{
				// This is a cardinality
				CardinalityPathElement cpe = new CardinalityPathElement();
				part = part.replace("[", "");
				part = part.replace("]", "");
				int card = Integer.parseInt(part);
				cpe.m_card = card;
				out_path.add(cpe);
			}
			else
			{
				// This is a key
				KeyPathElement kpe = new KeyPathElement();
				kpe.m_key = part;
				out_path.add(kpe);
			}
		}
		return out_path;
	}

	/**
	 * Retrieves part of a JSON element based on a path
	 * @param root The element to look into
	 * @param path A list of path elements representing the path to follow
	 * @return A JSON element corresponding to the end of the path,
	 *   or null if the path does not correspond to anything in
	 *   the JSON element
	 */
	public static JsonElement get(JsonElement root, List<PathElement> path)
	{
		if (path.isEmpty())
		{
			return root;
		}
		LinkedList<PathElement> new_path = new LinkedList<PathElement>(path);
		PathElement first_path_el = new_path.removeFirst();
		if (first_path_el instanceof KeyPathElement && root instanceof JsonMap)
		{
			KeyPathElement kpe = (KeyPathElement) first_path_el;
			JsonMap map = (JsonMap) root;
			JsonElement cursor = map.get(kpe.m_key);
			return get(cursor, new_path);
		}
		else if (first_path_el instanceof CardinalityPathElement && root instanceof JsonList)
		{
			CardinalityPathElement cpe = (CardinalityPathElement) first_path_el;
			JsonList list = (JsonList) root;
			JsonElement cursor = list.get(cpe.m_card);
			return get(cursor, new_path);      
		}
		return null;
	}

	/**
	 * Retrieves part of a JSON element based on a path and casts it as
	 * a string
	 * @param root The element to look into
	 * @param path A string representing the path to follow
	 * @return A string corresponding to the end of the path,
	 *   or null if the path does not correspond to anything in
	 *   the JSON element
	 */
	public static String getString(JsonElement root, String path)
	{
		JsonElement answer = get(root, path);
		if (answer == null || !(answer instanceof JsonString))
		{
			return null;
		}
		JsonString s_answer = (JsonString) answer;
		return s_answer.stringValue();
	}

	/**
	 * Retrieves part of a JSON element based on a path and casts it as
	 * a number
	 * @param root The element to look into
	 * @param path A string representing the path to follow
	 * @return A number corresponding to the end of the path,
	 *   or null if the path does not correspond to anything in
	 *   the JSON element
	 */
	public static Number getNumber(JsonElement root, String path)
	{
		JsonElement answer = get(root, path);
		if (answer == null || !(answer instanceof JsonNumber))
		{
			return null;
		}
		JsonNumber s_answer = (JsonNumber) answer;
		return s_answer.numberValue();
	}

	/**
	 * Retrieves part of a JSON element based on a path and casts it as
	 * a list
	 * @param root The element to look into
	 * @param path A string representing the path to follow
	 * @return A list corresponding to the end of the path,
	 *   or null if the path does not correspond to anything in
	 *   the JSON element
	 */
	public static JsonList getList(JsonElement root, String path)
	{
		JsonElement answer = get(root, path);
		if (answer == null || !(answer instanceof JsonList))
		{
			return null;
		}
		JsonList s_answer = (JsonList) answer;
		return s_answer;
	}

	/**
	 * Retrieves part of a JSON element based on a path and casts it as
	 * a map
	 * @param root The element to look into
	 * @param path A string representing the path to follow
	 * @return A map corresponding to the end of the path,
	 *   or null if the path does not correspond to anything in
	 *   the JSON element
	 */
	public static JsonMap getMap(JsonElement root, String path)
	{
		JsonElement answer = get(root, path);
		if (answer == null || !(answer instanceof JsonMap))
		{
			return null;
		}
		JsonMap s_answer = (JsonMap) answer;
		return s_answer;
	}

	protected static abstract class PathElement
	{
		public PathElement()
		{
			super();
		}
	}

	protected static class KeyPathElement extends PathElement
	{
		public String m_key;
		
		public KeyPathElement()
		{
			super();
			m_key = "";
		}
	}

	protected static class CardinalityPathElement extends PathElement
	{
		public int m_card;
		
		public CardinalityPathElement()
		{
			super();
			m_card = -1;
		}
	}
}
