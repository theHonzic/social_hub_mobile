//
//  RoundedButton.swift
//  iosApp
//
//  Created by Jan Janovec on 22.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RoundedButton<BackgroundShapeStyle: ShapeStyle, ForegroundShapeStyle: ShapeStyle>: View {
    var text: String
    var foreground: ForegroundShapeStyle
    var background: BackgroundShapeStyle
    var action: (() -> Void) = {}
    var body: some View {
        Button {
            action()
        } label: {
            Text(text)
                .font(.title3)
                .foregroundStyle(foreground)
                .fontWeight(.semibold)
                .frame(maxWidth: .infinity)
                .padding(.vertical, 8)
                .padding(.horizontal, 14)
                .background(background)
                .cornerRadius(30)
        }
        .buttonStyle(.plain)
    }
}

#Preview {
    VStack {
        RoundedButton(text: "Login", foreground: Color.white, background: LinearGradient(colors: [.red, .yellow], startPoint: .bottom, endPoint: .top))
        RoundedButton(text: "Login", foreground: Color.white, background: LinearGradient(colors: [.red, .yellow], startPoint: .bottom, endPoint: .top)) {}
    }
        .padding()
}
