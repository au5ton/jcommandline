//
//  Argument.java
//
//  Java Source File
//
//  Created by Austin Jackson
//
//  Further updates
//  http://github.com/MacPhage
//

package com.ruthlessphysics.jcommandline;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.ruthlessphysics.util.StringTools;

public class Argument
{

	public static void main(String args[])
	{
		try 
		{
			if(args[0].equals("abcdefg"))
			{
				//
			}
			global_args = args;
			make(global_args);
			//System.out.println("args[] applied globally...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No arguments were inputted. This is a command-line application.",
					Argument.class.getName(), JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		
	}

	public static List<Argument> arguments;
	public static String[] global_args;

	public Argument(String tag, String arg)
	{
		this.tag = tag;
		this.arg = arg;
	}
	public Argument(String tag, String arg, int index)
	{
		this.tag = tag;
		this.arg = arg;
		this.index = index;
	}
	public Argument(int index)
	{
		this.index = index;
	}

	public String getTag()
	{
		return this.tag;
	}
	public String getArg()
	{
		return this.arg;
	}

	private String tag = "";
	private String arg = "";
	private int index;


	public static void make()
	{
		make(global_args);
	}
	public static void make(String[] args)
	{
		List<Integer> regestry = new ArrayList<Integer>();
		Integer[] reg = {new Integer(0)};
		try
		{
			System.out.print("Registering argument indexes...");
			for(int i = 0; i < args.length; i++)
			{
				if(args[i].charAt(0) == '-')
				{
					regestry.add(new Integer(i)); 
				}
			}
			System.out.println("Done.");
			
			for(int i = 0; i < regestry.size(); i++)
			{
				System.out.println(i+":"+regestry.get(i).intValue());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("FAILED!");
		}

		try
		{
			System.out.print("Converting argument list to an array");
			reg = regestry.toArray();
			System.out.println("Done.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("FAILED!");
		}

		try
		{
			System.out.print("Creating Argument instances...");
			String tag = "";
			String content = "";
			boolean flag = false;
			for(int i = 0; i < reg.length; i++)
			{
				tag = args[i];
				int scanner = i;
				while(!flag)
				{
					if(args[scanner].charAt(0) == '-')
					{
						break;
					}
					else
					{
						scanner++;
					}
				}
				int to = scanner;
				String[] cut = (String[]) StringTools.cutout(args, i, to);
				content = StringTools.fuse(cut, StringTools.WITH_SPACES);
				arguments.add(new Argument(tag, content));
			}

			System.out.println("Done.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("FAILED!");
		}

	}
}
