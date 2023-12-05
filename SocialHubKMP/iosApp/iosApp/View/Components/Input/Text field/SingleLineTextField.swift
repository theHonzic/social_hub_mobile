//
//  SingleLineTextField.swift
//  iosApp
//
//  Created by Jan Janovec on 23.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SingleLineTextField<BackgroundStyle: ShapeStyle, ForegroundStyle: ShapeStyle>: View {
    @Binding var text: String
    @FocusState var focus
    var placeholder: String
    var background: BackgroundStyle
    var foreground: ForegroundStyle
    var placeholderColor: Color = .gray
    var icon: Image?
    var body: some View {
        HStack(alignment: .center, spacing: 8) {
            if let image = icon, !focus {
                icon
                    .foregroundStyle(foreground)
                    .scaledToFit()
            }
            TextField(
                "",
                text: $text,
                prompt: Text(placeholder)
                    .foregroundStyle(placeholderColor)
            )
            .focused($focus)
            .foregroundStyle(foreground)
            .textFieldStyle(.plain)
        }
        .padding(.vertical, 8)
        .padding(.horizontal, 14)            .background(background)
        .clipShape(RoundedRectangle(cornerRadius: 30))
        .animation(.default, value: focus)
    }
}

#Preview {
    VStack(spacing: 20) {
        SingleLineTextField(
            text: .constant(""),
            placeholder: "Email", background: LinearGradient(colors: [.blue, .red], startPoint: .leading, endPoint: .trailing),
            foreground: Color.white,
            icon: .init(systemName: "shield")
        )
        SingleLineTextField(
            text: .constant("fdashfhdsu hfadhsl"),
            placeholder: "Email", background: LinearGradient(colors: [.blue, .red], startPoint: .leading, endPoint: .trailing),
            foreground: Color.white
        )
        
    }
    .padding()
    .background {
        Color.green
    }
}
