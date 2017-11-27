package trial.opengl.util;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glValidateProgram;
import static trial.opengl.util.LoggerConfig.Log;

/**
 * Created by jprince on 08-Aug-17.
 */

public class ShaderHelper {
    private static final String tag = "ShaderHelper";

    public static int compileFragmentShader(String shaderSource){
        return compileShader(GL_FRAGMENT_SHADER, shaderSource);
    }

    public static int compileVertexShader(String shaderSource){
        return compileShader(GL_VERTEX_SHADER, shaderSource);
    }

    private static int compileShader(int type, String shaderSource){
        final int shaderObjectId = glCreateShader(type);

        if(shaderObjectId == 0){
            Log(tag, "Could not create new shader");
            return 0;
        }
        glShaderSource(shaderObjectId, shaderSource);
        glCompileShader(shaderObjectId);
        final int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);
        Log(tag, glGetShaderInfoLog(shaderObjectId));

        if(compileStatus[0] == 0){
            glDeleteShader(shaderObjectId);
            Log(tag, "shader compilation failed");
            return 0;
        }
        return shaderObjectId;
    }

    public static int linkProgram(int vertexShader, int fragmentShader){
        final int programObjectId = glCreateProgram();

        if(programObjectId ==0){
            Log(tag, "Could not create new program");
            return 0;
        }

        glAttachShader(programObjectId, vertexShader);
        glAttachShader(programObjectId, fragmentShader);
        glLinkProgram(programObjectId);

        int[] linkstatus = new int[1];
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkstatus, 0);
        Log(tag, glGetProgramInfoLog(programObjectId));
        if(linkstatus[0] == 0){
            glDeleteProgram(programObjectId);
           Log(tag, "Program could not be linked");
            return 0;
        }
        return programObjectId;
    }

    public static boolean validateProgram(int programbjectId){
        glValidateProgram(programbjectId);

        final int[] validateStatus = new int[1];
        glGetProgramiv(programbjectId, GL_VALIDATE_STATUS, validateStatus, 0);
        LoggerConfig.Log(tag, glGetProgramInfoLog(programbjectId));
        return validateStatus[0] != 0;
    }
}
