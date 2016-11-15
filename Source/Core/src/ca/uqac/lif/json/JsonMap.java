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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonMap extends JsonElement implements Map<String,JsonElement>
{
	protected Map<String,JsonElement> m_map;

	public JsonMap()
	{
		super();
		m_map = new HashMap<String,JsonElement>();
	}

	@Override
	public String toString(String indent, boolean compact)
	{
		StringBuilder out = new StringBuilder();
		out.append(indent).append("{");
		if (!compact)
			out.append("\n");
		boolean first = true;
		for (String k : keySet())
		{
			if (first)
				first = false;
			else
			{
				out.append(",");
				if (!compact)
				{
					out.append("\n");
				}
			}
			JsonElement v = get(k);
			if (!compact)
			{
				out.append(indent).append("  ").append("\"").append(k).append("\" : ");
				out.append(v.toString(indent + "    ", compact));
				out.append("\n").append(indent);
			}
			else
			{
				out.append("\"").append(k).append("\":");
				out.append(v.toString("", compact));
			}
		}
		out.append("}");
		return out.toString();
	}

	public JsonElement put(String arg0, Number arg1)
	{
		return put(arg0, new JsonNumber(arg1));
	}

	public JsonElement put(String arg0, Boolean arg1)
	{
		if (arg1)
		{
			return put(arg0, new JsonString("true"));      
		}
		return put(arg0, new JsonString("false"));
	}

	public JsonElement put(String arg0, String arg1)
	{
		return put(arg0, new JsonString(arg1));
	}

	public JsonElement put(String arg0, Object arg1)
	{
		if (arg1 instanceof JsonElement)
		{
			return put(arg0, (JsonElement) arg1);
		}
		else if (arg1 instanceof Number)
		{
			return put(arg0, (Number) arg1);
		}
		return put(arg0, arg1.toString());
	}

	@Override
	public void clear()
	{
		m_map.clear();
	}

	@Override
	public boolean containsKey(Object arg0)
	{
		return m_map.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0)
	{
		return m_map.containsValue(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<String, JsonElement>> entrySet()
	{
		return m_map.entrySet();
	}

	@Override
	public JsonElement get(Object arg0)
	{
		return m_map.get(arg0);
	}

	public String getString(Object arg0)
	{
		return ((JsonString) m_map.get(arg0)).stringValue();
	}

	public Number getNumber(Object arg0)
	{
		return ((JsonNumber) m_map.get(arg0)).numberValue();
	}

	public int getInt(Object arg0)
	{
		return getNumber(arg0).intValue();
	}

	@Override
	public boolean isEmpty()
	{
		return m_map.isEmpty();
	}

	@Override
	public Set<String> keySet()
	{
		return m_map.keySet();
	}

	@Override
	public JsonElement put(String arg0, JsonElement arg1)
	{
		return m_map.put(arg0, arg1);
	}

	@Override
	public void putAll(Map<? extends String, ? extends JsonElement> arg0)
	{
		m_map.putAll(arg0);
	}

	@Override
	public JsonElement remove(Object arg0)
	{
		return m_map.remove(arg0);
	}

	@Override
	public int size()
	{
		return m_map.size();
	}

	@Override
	public Collection<JsonElement> values()
	{
		return m_map.values();
	}

	@Override
	public int compareTo(JsonElement e) 
	{
		if (equals(e))
		{
			return 0;
		}
		if (e instanceof JsonNull || e instanceof JsonFalse || 
				e instanceof JsonTrue || e instanceof JsonNumber 
				|| e instanceof JsonString || e instanceof JsonList)
		{
			return 1;
		}
		if (e instanceof JsonMap)
		{
			JsonMap other_map = (JsonMap) e;
			int my_size = size();
			int other_size = other_map.size();
			if (my_size < other_size)
			{
				return -1;
			}
			if (my_size == other_size)
			{
				return 0;
			}
			return 1;
		}
		return -1;
	}
}
