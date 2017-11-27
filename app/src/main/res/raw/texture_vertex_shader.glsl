uniform mat4 u_Matrix;

attribute vec4 a_Position;
attribute vec4 a_TextureCoordinates;

varying vec4 v_TextureCoordinates;

void main() {
    v_TextureCoordinates = a_TextureCoordinates;
    gl_Postion = u_Matrix* a_Position;
}
